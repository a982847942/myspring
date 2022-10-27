package nuaa.edu.springframework.beans.factory;

import nuaa.edu.springframework.beans.BeansException;

/**
 * @Classname BeanFactroyAware
 * @Description
 * @Date 2022/10/21 13:13
 * @Created by brain
 */
public interface BeanFactoryAware extends Aware{
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
