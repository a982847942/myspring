package nuaa.edu.springframework.beans.factory.config;

import nuaa.edu.springframework.beans.factory.support.BeanDefinition;

/**
 * @Classname BeanDefinitionRegistry
 * @Description
 * @Date 2022/10/13 15:36
 * @Created by brain
 */
public interface BeanDefinitionRegistry {
    void registryBeanDefinition(String beanName, BeanDefinition beanDefinition);
    boolean containsBeanDefinition(String beanName);
}
