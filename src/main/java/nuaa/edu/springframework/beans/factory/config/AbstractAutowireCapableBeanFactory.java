package nuaa.edu.springframework.beans.factory.config;

import cn.hutool.core.bean.BeanUtil;
import nuaa.edu.springframework.beans.BeansException;
import nuaa.edu.springframework.beans.PropertyValue;
import nuaa.edu.springframework.beans.PropertyValues;
import nuaa.edu.springframework.beans.factory.support.BeanDefinition;
import nuaa.edu.springframework.beans.factory.support.InstantiationStrategy;
import nuaa.edu.springframework.beans.factory.support.SimpleInstantiationStrategy;

import java.lang.reflect.Constructor;

/**
 * @Classname AbstractAutowireCapableBeanFactory
 * @Description
 * @Date 2022/10/13 15:34
 * @Created by brain
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    InstantiationStrategy defaultStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object creatBean(String beanName, BeanDefinition beanDefinition, Object... args) throws BeansException {
        Object bean;
        try {
            //实例化bean
//            bean = beanDefinition.getBeanClass().newInstance();
            bean = creatBeanInstance(beanName, beanDefinition, args);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        //填充属性
        applyPropertyValues(beanName, bean, beanDefinition);
        //添加到单例池
        addSingleton(beanName, bean);
        return bean;
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
}
