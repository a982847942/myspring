package nuaa.edu.springframework.xmltest;

import nuaa.edu.springframework.beans.BeansException;
import nuaa.edu.springframework.beans.factory.support.BeanDefinition;

/**
 * @Classname
 * @Description
 * @Date 2022/10/20 21:47
 * @Created by brain
 */
public interface TConfigurableListableBeanFactory extends TConfigurableBeanFactory,TListableBeanFactory,TAutowireCapableBeanFactory{
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    //提前实例化单例bean对象
    void preInstantiateSingletons() throws BeansException;
}
