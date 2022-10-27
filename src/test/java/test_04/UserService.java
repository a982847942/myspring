package test_04;

import nuaa.edu.springframework.beans.BeansException;
import nuaa.edu.springframework.beans.factory.*;
import nuaa.edu.springframework.context.ApplicationContext;
import nuaa.edu.springframework.context.ApplicationContextAware;

/**
 * @Classname UserService
 * @Description
 * @Date 2022/10/19 16:03
 * @Created by brain
 */
public class UserService implements BeanNameAware, BeanClassloaderAware, ApplicationContextAware, BeanFactoryAware {

    private String uId;
    private String company;
    private String location;
    private test_04.UserDao userDao;
    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;


    public String queryUserInfo() {
        return userDao.queryUserName(uId) + ", 公司：" + company + ", 地点" + location;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Bean Name is：" + name);
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("ClassLoader：" + classLoader);
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
