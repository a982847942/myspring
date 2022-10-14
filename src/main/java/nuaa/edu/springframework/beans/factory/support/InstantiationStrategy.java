package nuaa.edu.springframework.beans.factory.support;

import java.lang.reflect.Constructor;

/**
 * @Classname InstantiationStrategy
 * @Description
 * @Date 2022/10/13 16:45
 * @Created by brain
 */
public interface InstantiationStrategy {
    Object instantiate(String beanName, BeanDefinition beanDefinition, Constructor ctr,Object...args);
}
