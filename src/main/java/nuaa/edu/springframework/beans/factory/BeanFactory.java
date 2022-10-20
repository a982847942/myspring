package nuaa.edu.springframework.beans.factory;

import nuaa.edu.springframework.beans.BeansException;
import nuaa.edu.springframework.beans.factory.support.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Classname BeanFactory
 * @Description
 * @Date 2022/10/13 15:08
 * @Created by brain
 */
public interface  BeanFactory {
//    Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
//    public Object getBean(String beanName){
//        return beanDefinitionMap.get(beanName).getBean();
//    }
//
//    public void registerBeanDefinition(String beanName,BeanDefinition beanDefinition){
//        beanDefinitionMap.put(beanName,beanDefinition);
//    }
   Object getBean(String beanName);
   Object getBean(String beanName,Object...args);
   <T> T getBean(String name, Class<T> requiredType) throws BeansException;
}
