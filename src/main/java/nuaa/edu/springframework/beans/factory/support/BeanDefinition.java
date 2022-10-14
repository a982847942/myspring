package nuaa.edu.springframework.beans.factory.support;

import nuaa.edu.springframework.beans.PropertyValue;
import nuaa.edu.springframework.beans.PropertyValues;

/**
 * @Classname BeanDefinition
 * @Description
 * @Date 2022/10/13 15:07
 * @Created by brain
 */
public class BeanDefinition {
//    Object bean;
//
//    public BeanDefinition(Object bean) {
//        this.bean = bean;
//    }
//
//    public Object getBean() {
//        return bean;
//    }

    private Class beanClass;
    private PropertyValues propertyValues;

    public Class getBeanClass() {
        return beanClass;
    }

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
