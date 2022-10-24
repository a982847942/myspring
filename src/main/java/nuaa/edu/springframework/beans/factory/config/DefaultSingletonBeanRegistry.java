package nuaa.edu.springframework.beans.factory.config;

import nuaa.edu.springframework.beans.BeansException;
import nuaa.edu.springframework.beans.factory.DisposableBean;
import nuaa.edu.springframework.beans.factory.support.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Classname DefaultSingletonBeanRegistry
 * @Description
 * @Date 2022/10/13 15:32
 * @Created by brain
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    /**
     * Internal marker for a null singleton object:
     * used as marker value for concurrent Maps (which don't support null values).
     * concurrentHashMap不支持null键  null值
     */
    protected static final Object NULL_OBJECT = new Object();
    //单例池对象
    private Map<String,Object> singletonObjects = new HashMap<>();
    /**
     * 单例对象且实现了DisposableBean接口或xml配置了destroy方法  这里DisposableBean其实都是DisposableBeanAdapter对象
     */
    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();
    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }

    protected void addSingleton(String beanName,Object singletonObject){
        singletonObjects.put(beanName,singletonObject);
    }
    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }


    public void destroySingletons() {
        Set<String> keySet = this.disposableBeans.keySet();
        Object[] disposableBeanNames = keySet.toArray();

        for (int i = disposableBeanNames.length - 1; i >= 0; i--) {
            Object beanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }
}
