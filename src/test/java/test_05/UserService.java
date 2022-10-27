package test_05;

import nuaa.edu.springframework.beans.factory.DisposableBean;
import nuaa.edu.springframework.beans.factory.InitializingBean;
import test_03.UserDao;

/**
 * @Classname UserService
 * @Description
 * @Date 2022/10/19 16:03
 * @Created by brain
 */
public class UserService{

    private String uId;
    private String company;
    private String location;
    private IUserDao userDao;
    public String queryUserInfo() {
        return userDao.queryUserName(uId) + "," + company + "," + location;
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

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }
}
