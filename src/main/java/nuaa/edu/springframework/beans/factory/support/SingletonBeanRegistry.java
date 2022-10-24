package nuaa.edu.springframework.beans.factory.support;

/**
 * @Classname SingletonBeanRegistry
 * @Description
 * @Date 2022/10/13 15:31
 * @Created by brain
 */
public interface SingletonBeanRegistry {
    Object getSingleton(String beanName);
    void registerSingleton(String beanName, Object singletonObject);
}
