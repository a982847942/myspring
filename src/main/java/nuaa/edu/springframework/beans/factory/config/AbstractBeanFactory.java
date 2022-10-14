package nuaa.edu.springframework.beans.factory.config;

import nuaa.edu.springframework.beans.factory.BeanFactory;
import nuaa.edu.springframework.beans.factory.support.BeanDefinition;

/**
 * @Classname AbstractBeanFactory
 * @Description
 * @Date 2022/10/13 15:32
 * @Created by brain
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String beanName) {
       return doGetBean(beanName,null);
    }

    @Override
    public Object getBean(String beanName, Object... args) {
        return doGetBean(beanName,args);
    }

    protected Object doGetBean(String beanName, Object... args){
        Object bean = getSingleton(beanName);
        if (bean != null){
            return bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return creatBean(beanName,beanDefinition,args);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName);
    protected abstract Object creatBean(String beanName,BeanDefinition beanDefinition,Object...args);

}
