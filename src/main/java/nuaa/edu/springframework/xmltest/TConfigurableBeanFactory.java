package nuaa.edu.springframework.xmltest;

import nuaa.edu.springframework.beans.factory.BeanFactory;
import nuaa.edu.springframework.beans.factory.config.BeanPostProcessor;
import nuaa.edu.springframework.beans.factory.support.SingletonBeanRegistry;

/**
 * @Classname ConfigurableBeanFactory
 * @Description
 * @Date 2022/10/20 21:39
 * @Created by brain
 */
public interface TConfigurableBeanFactory extends THierarchicalBeanFactory, SingletonBeanRegistry {
    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
