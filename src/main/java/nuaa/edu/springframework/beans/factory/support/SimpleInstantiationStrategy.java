package nuaa.edu.springframework.beans.factory.support;

import nuaa.edu.springframework.beans.BeansException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Classname SimpleInstantiationStrategy
 * @Description
 * @Date 2022/10/13 16:47
 * @Created by brain
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {
    @Override
    public Object instantiate(String beanName, BeanDefinition beanDefinition, Constructor ctr, Object... args) throws BeansException {
        Class clazz = beanDefinition.getBeanClass();
        try {
            if (ctr != null){
                //有参数
                return clazz.getDeclaredConstructor(ctr.getParameterTypes()).newInstance(args);
            }
            //无参数
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new BeansException("Failed to instantiate [" + clazz.getName() + "]", e);
        }
    }
}
