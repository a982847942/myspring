package nuaa.edu.springframework.beans.factory.config;

import nuaa.edu.springframework.beans.BeansException;
import nuaa.edu.springframework.beans.factory.BeanFactory;
import nuaa.edu.springframework.beans.factory.support.BeanDefinition;
import nuaa.edu.springframework.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname AbstractBeanFactory
 * @Description
 * @Date 2022/10/13 15:32
 * @Created by brain
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {
    /** BeanPostProcessors to apply in createBean */
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

    /**
     * String resolvers to apply e.g. to annotation attribute values
     */
//    private List<StringValueResolver> embeddedValueResolvers = new ArrayList<>();

    private ClassLoader classLoader = ClassUtils.getDefaultClassLoader();

    @Override
    public Object getBean(String beanName) {
       return doGetBean(beanName,null);
    }

    @Override
    public Object getBean(String beanName, Object... args) {
        return doGetBean(beanName,args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T)getBean(name);
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

    // TODO: 2022/10/20
    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

//    @Override
//    public void addEmbeddedValueResolver(StringValueResolver valueResolver){
//        this.embeddedValueResolvers.add(valueResolver);
//    }
//
//
//    @Override
//    public String resolveEmbeddedValue(String value) {
//        String result = value;
//        for (StringValueResolver resolver : this.embeddedValueResolvers) {
//            result = resolver.resolveStringValue(result);
//        }
//        return result;
//    }
    /**
     * Return the list of BeanPostProcessors that will get applied
     * to beans created with this factory.
     */
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    public ClassLoader getClassLoader() {
        return this.classLoader;
    }

}
