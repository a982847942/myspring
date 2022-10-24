package nuaa.edu.springframework.beans.factory.config;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import nuaa.edu.springframework.beans.BeansException;
import nuaa.edu.springframework.beans.PropertyValue;
import nuaa.edu.springframework.beans.PropertyValues;
import nuaa.edu.springframework.beans.factory.*;
import nuaa.edu.springframework.beans.factory.support.BeanDefinition;
import nuaa.edu.springframework.beans.factory.support.DisposableBeanAdapter;
import nuaa.edu.springframework.beans.factory.support.InstantiationStrategy;
import nuaa.edu.springframework.beans.factory.support.SimpleInstantiationStrategy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @Classname AbstractAutowireCapableBeanFactory
 * @Description
 * @Date 2022/10/13 15:34
 * @Created by brain
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {
    InstantiationStrategy defaultStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object creatBean(String beanName, BeanDefinition beanDefinition, Object... args) throws BeansException {
        Object bean;
        try {
            //实例化bean 这里的BeanDefinition已经被改变了
//            bean = beanDefinition.getBeanClass().newInstance();
            bean = creatBeanInstance(beanName, beanDefinition, args);
            //填充属性
            applyPropertyValues(beanName, bean, beanDefinition);
            // 执行 Bean 的初始化方法和 BeanPostProcessor 的前置和后置处理方法
            bean = initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        // 注册实现了 DisposableBean 接口的 Bean 对象
        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);
        //添加到单例池
        // 判断 SCOPE_SINGLETON、SCOPE_PROTOTYPE
        if (beanDefinition.isSingleton()) {
            addSingleton(beanName, bean);
        }
        return bean;
    }
    protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 非 Singleton 类型的 Bean 不执行销毁方法
        if (!beanDefinition.isSingleton()) return;
        if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
        }
    }


    public void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues pv = beanDefinition.getPropertyValues();
            PropertyValue[] propertyValues = pv.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                if (value instanceof BeanReference) {
                    BeanReference reference = (BeanReference) value;
                    value = getBean(reference.getBeanName());
                }
                //hutools
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values：" + beanName);
        }
    }

    public Object creatBeanInstance(String beanName, BeanDefinition beanDefinition, Object... args) {
        Constructor constructorToUse = null;
        Class clazz = beanDefinition.getBeanClass();
        Constructor[] ctrs = clazz.getDeclaredConstructors();
        for (Constructor ctr : ctrs) {
            if (null != args && ctr.getParameterTypes().length == args.length) {
                boolean flag = true;
                Class[] parameterTypes = ctr.getParameterTypes();
                for (int i = 0; i < parameterTypes.length; i++) {
                    if (parameterTypes[i] != args[i].getClass()) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    constructorToUse = ctr;
                    constructorToUse.setAccessible(true);
                    break;
                }
            }
        }
        return defaultStrategy.instantiate(beanName, beanDefinition, constructorToUse, args);
    }

    public void setDefaultStrategy(InstantiationStrategy defaultStrategy) {
        this.defaultStrategy = defaultStrategy;
    }

    public InstantiationStrategy getDefaultStrategy() {
        return defaultStrategy;
    }


    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        // invokeAwareMethods
        if (bean instanceof Aware) {
            if (bean instanceof BeanFactoryAware) {
                ((BeanFactoryAware) bean).setBeanFactory(this);
            }
            if (bean instanceof BeanClassloaderAware){
                ((BeanClassloaderAware) bean).setBeanClassLoader(getClassLoader());
            }
            if (bean instanceof BeanNameAware) {
                ((BeanNameAware) bean).setBeanName(beanName);
            }
        }



        // 1. 执行 BeanPostProcessor Before 处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        // 待完成内容：invokeInitMethods(beanName, wrappedBean, beanDefinition);
        try {
            invokeInitMethods(beanName, wrappedBean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Invocation of init method of bean[" + beanName + "] failed", e);
        }

        // 2. 执行 BeanPostProcessor After 处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        return wrappedBean;
    }

    private void invokeInitMethods(String beanName, Object wrappedBean, BeanDefinition beanDefinition) throws Exception {


        //接口实现的InitMethod
        if (wrappedBean instanceof InitializingBean) {
            ((InitializingBean) wrappedBean).afterPropertiesSet();
        }
        //XML配置的InitMethod
        String initMethodName = beanDefinition.getInitMethodName();
        if (StrUtil.isNotEmpty(initMethodName)){
            Method initMethod = beanDefinition.getBeanClass().getDeclaredMethod(initMethodName);
            if (initMethod == null){
                throw new BeansException("Could not find an init method named '" + initMethodName + "' on bean with name '" + beanName + "'");
            }
            //initMethod一般没有参数
            initMethod.invoke(wrappedBean);
        }
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }
}
