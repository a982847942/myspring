package nuaa.edu.springframework.beans.factory.support;

import nuaa.edu.springframework.beans.BeansException;
import nuaa.edu.springframework.beans.factory.FactoryBean;
import nuaa.edu.springframework.beans.factory.config.DefaultSingletonBeanRegistry;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Classname FactoryBeanRegistrySupport
 * @Description
 * @Date 2022/10/21 14:30
 * @Created by brain
 */
public abstract class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {
    /**
     * Cache of singleton objects created by FactoryBeans: FactoryBean name --> object
     */
    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<String, Object>();

    protected Object getCacheObjectForFactoryBean(String beanName){
        Object bean = this.factoryBeanObjectCache.get(beanName);
        return bean == NULL_OBJECT ? null : bean;
    }
    protected Object getObjectFromFactoryBean(FactoryBean factory,String beanName){
        if (factory.isSingleton()){
            Object cacheBean = getCacheObjectForFactoryBean(beanName);
            if (cacheBean == null){
                cacheBean = doGetObjectFromFactoryBean(factory, beanName);
                this.factoryBeanObjectCache.put(beanName,(cacheBean == null ? NULL_OBJECT : cacheBean));
            }
            return cacheBean;
        }
        return doGetObjectFromFactoryBean(factory, beanName);
    }

    protected  Object doGetObjectFromFactoryBean(FactoryBean factory, String beanName){
        try {
           return factory.getObject();
        } catch (Exception e) {
            throw new BeansException("FactoryBean threw exception on object[" + beanName + "] creation", e);
        }
    }


//    protected Object getCachedObjectForFactoryBean(String beanName) {
//        Object object = this.factoryBeanObjectCache.get(beanName);
//        return (object != NULL_OBJECT ? object : null);
//    }
//
//    protected Object getObjectFromFactoryBean(FactoryBean factory, String beanName) {
//        if (factory.isSingleton()) {
//            Object object = this.factoryBeanObjectCache.get(beanName);
//            if (object == null) {
//                object = doGetObjectFromFactoryBean(factory, beanName);
//                this.factoryBeanObjectCache.put(beanName, (object != null ? object : NULL_OBJECT));
//            }
//            return (object != NULL_OBJECT ? object : null);
//        } else {
//            return doGetObjectFromFactoryBean(factory, beanName);
//        }
//    }
//
//    private Object doGetObjectFromFactoryBean(final FactoryBean factory, final String beanName){
//        try {
//            return factory.getObject();
//        } catch (Exception e) {
//            throw new BeansException("FactoryBean threw exception on object[" + beanName + "] creation", e);
//        }
//    }
}
