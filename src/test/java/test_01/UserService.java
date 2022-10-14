package test_01;

/**
 * @Classname UserService
 * @Description
 * @Date 2022/10/13 15:13
 * @Created by brain
 */
public class UserService {
    private String name;
    private Integer age;
    private UserDao userDao;
    private String uid;

    public  void  queryInfo(){
        System.out.println("用户姓名:" + name + "年龄:" + age);
    }
    public  void  queryInfo2(){
        System.out.println("用户uid：" + userDao.queryUserName(uid));
    }

    public UserService() {
    }

    public UserService(String name) {
        this.name = name;
    }

    public UserService(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
    public UserService(Integer age, String name) {
        this.name = name;
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "UserService{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
