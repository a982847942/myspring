package nuaa.edu.springframework.beans.factory;

import nuaa.edu.springframework.beans.BeansException;

import java.util.Map;

/**
 * @Classname ListableBeanFactory
 * @Description
 * @Date 2022/10/19 15:21
 * @Created by brain
 */
// TODO: 2022/10/19
public interface ListableBeanFactory extends BeanFactory{
    /**
     * 按照类型返回 Bean 实例
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * Return the names of all beans defined in this registry.
     *
     * 返回注册表中所有的Bean名称
     */
    String[] getBeanDefinitionNames();
}
