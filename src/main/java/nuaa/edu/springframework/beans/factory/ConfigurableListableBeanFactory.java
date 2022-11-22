package nuaa.edu.springframework.beans.factory;

import nuaa.edu.springframework.beans.BeansException;
import nuaa.edu.springframework.beans.factory.config.AutowireCapableBeanFactory;
import nuaa.edu.springframework.beans.factory.config.ConfigurableBeanFactory;
import nuaa.edu.springframework.beans.factory.support.BeanDefinition;

/**
 * @Classname ConfigurableListableBeanFactory
 * @Description
 * @Date 2022/10/19 15:29
 * @Created by brain
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    //提前实例化单例bean对象
    void preInstantiateSingletons() throws BeansException;

}
