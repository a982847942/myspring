package test_03;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname UserDao
 * @Description
 * @Date 2022/10/13 19:47
 * @Created by brain
 */
public class UserDao {
    private static Map<String, String> hashMap = new HashMap<>();

    public void initDataMethod() {
        System.out.println("执行：init-method");
        hashMap.put("10001", "赵总");
        hashMap.put("10002", "八杯水");
        hashMap.put("10003", "阿毛");
    }
    public void destroyDataMethod(){
        System.out.println("执行：destroy-method");
        hashMap.clear();
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }
}

