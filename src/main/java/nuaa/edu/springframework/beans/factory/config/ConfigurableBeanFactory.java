package nuaa.edu.springframework.beans.factory.config;

import nuaa.edu.springframework.beans.factory.HierarchicalBeanFactory;
import nuaa.edu.springframework.beans.factory.support.SingletonBeanRegistry;
import nuaa.edu.springframework.util.StringValueResolver;

/**
 * @Classname ConfigurableBeanFactory
 * @Description
 * @Date 2022/10/19 15:16
 * @Created by brain
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    //添加beanPostProcessor
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
    //销毁单例对象
    void destroySingletons();

    /**
     * Add a String resolver for embedded values such as annotation attributes.
     * @param valueResolver the String resolver to apply to embedded values
     * @since 3.0
     */
    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    /**
     * Resolve the given embedded value, e.g. an annotation attribute.
     * @param value the value to resolve
     * @return the resolved value (may be the original value as-is)
     * @since 3.0
     */
    String resolveEmbeddedValue(String value);
}
