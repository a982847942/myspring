package nuaa.edu.springframework.beans.factory.support;

import nuaa.edu.springframework.beans.BeansException;
import nuaa.edu.springframework.beans.factory.config.BeanDefinitionRegistry;
import nuaa.edu.springframework.core.io.Resource;
import nuaa.edu.springframework.core.io.ResourceLoader;

/**
 * @Classname BeanDefinitionReader
 * @Description
 * @Date 2022/10/14 20:42
 * @Created by brain
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();
    ResourceLoader getResourceLoader();

    void loadBeanDefinition(Resource resource) throws BeansException;
    void loadBeanDefinition(Resource ...resources) throws BeansException;
    void loadBeanDefinition(String locations) throws BeansException;
    void loadBeanDefinitions(String[] locations) throws BeansException;
}
