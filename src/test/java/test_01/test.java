package test_01;

import nuaa.edu.springframework.beans.PropertyValue;
import nuaa.edu.springframework.beans.PropertyValues;
import nuaa.edu.springframework.beans.factory.config.BeanReference;
import nuaa.edu.springframework.beans.factory.config.DefaultListableBeanFactory;
import nuaa.edu.springframework.beans.factory.support.BeanDefinition;
import nuaa.edu.springframework.beans.factory.BeanFactory;
import org.junit.Test;

/**
 * @Classname xmltest
 * @Description
 * @Date 2022/10/13 15:14
 * @Created by brain
 */
public class test {

    @Test
    public void test(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registryBeanDefinition("userService",new BeanDefinition(UserService.class));
        UserService userService = (UserService) beanFactory.getBean("userService","哈哈",12);
        userService.queryInfo();
    }
    @Test
    public void test2(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registryBeanDefinition("userDao",new BeanDefinition(UserDao.class));

        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uid", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));

        beanFactory.registryBeanDefinition("userService",new BeanDefinition(UserService.class,propertyValues));
        UserService userService = (UserService) beanFactory.getBean("userService","哈哈");
        userService.queryInfo();
        userService.queryInfo2();
    }

}
