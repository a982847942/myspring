package nuaa.edu.springframework.beans.factory.support;

import nuaa.edu.springframework.beans.PropertyValue;
import nuaa.edu.springframework.beans.PropertyValues;
import nuaa.edu.springframework.beans.factory.config.ConfigurableBeanFactory;

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
    private String initMethodName;
    private String destroyMethodName;
    String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;

    String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;
    private String scope = SCOPE_SINGLETON;
    private boolean singleton = true;

    private boolean prototype = false;

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

    public boolean isSingleton() {
        return singleton;
    }

    public boolean isPrototype() {
        return prototype;
    }

    //在xml注册Bean定义时，通过scope字段来判断是单例还是原型
    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public String getInitMethodName() {
        return initMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public String getDestroyMethodName() {
        return destroyMethodName;
    }

    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
