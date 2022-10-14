package nuaa.edu.springframework.beans.factory.config;

import nuaa.edu.springframework.beans.factory.support.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname DefaultSingletonBeanRegistry
 * @Description
 * @Date 2022/10/13 15:32
 * @Created by brain
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    //单例池对象
    private Map<String,Object> singletonObjects = new HashMap<>();
    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }
    protected void addSingleton(String beanName,Object singletonObject){
        singletonObjects.put(beanName,singletonObject);
    }
}
