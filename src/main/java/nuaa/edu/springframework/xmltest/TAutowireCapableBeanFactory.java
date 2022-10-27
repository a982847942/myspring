package nuaa.edu.springframework.xmltest;

import nuaa.edu.springframework.beans.BeansException;
import nuaa.edu.springframework.beans.factory.BeanFactory;

/**
 * @Classname AutowireCapableBeanFactory
 * @Description
 * @Date 2022/10/20 21:42
 * @Created by brain
 */
public interface TAutowireCapableBeanFactory extends BeanFactory {
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;
}
