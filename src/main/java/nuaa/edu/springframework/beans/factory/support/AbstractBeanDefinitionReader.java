package nuaa.edu.springframework.beans.factory.support;

import nuaa.edu.springframework.beans.BeansException;
import nuaa.edu.springframework.beans.factory.config.BeanDefinitionRegistry;
import nuaa.edu.springframework.core.io.DefaultResourceLoader;
import nuaa.edu.springframework.core.io.Resource;
import nuaa.edu.springframework.core.io.ResourceLoader;

/**
 * @Classname AbstractBeanDefinitonReader
 * @Description
 * @Date 2022/10/14 20:46
 * @Created by brain
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{
    private BeanDefinitionRegistry registry;
    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry,new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    public void setRegistry(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return this.resourceLoader;
    }


}
