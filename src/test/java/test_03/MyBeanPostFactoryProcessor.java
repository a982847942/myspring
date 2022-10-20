package test_03;

import nuaa.edu.springframework.beans.BeansException;
import nuaa.edu.springframework.beans.PropertyValue;
import nuaa.edu.springframework.beans.PropertyValues;
import nuaa.edu.springframework.beans.factory.ConfigurableListableBeanFactory;
import nuaa.edu.springframework.beans.factory.config.BeanFactoryPostProcessor;
import nuaa.edu.springframework.beans.factory.config.ConfigurableBeanFactory;
import nuaa.edu.springframework.beans.factory.support.BeanDefinition;

/**
 * @Classname MyBeanPostFactoryProcessor
 * @Description
 * @Date 2022/10/19 16:04
 * @Created by brain
 */
public class MyBeanPostFactoryProcessor  implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.setPropertyValue("company","改为：字节跳动");

//        propertyValues.addPropertyValue(new PropertyValue("company", "改为：字节跳动"));
    }
}
