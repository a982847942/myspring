package test_01;

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

    static {
        hashMap.put("10001", "小傅哥");
        hashMap.put("10002", "八杯水");
        hashMap.put("10003", "阿毛");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }
}

