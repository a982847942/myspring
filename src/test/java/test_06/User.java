package test_06;

/**
 * @Classname User
 * @Description
 * @Date 2022/10/22 10:37
 * @Created by brain
 */
public class User {
    private int id;
    private String name;

    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    void receiveMessage(String message){
        System.out.println("收到消息了！");
        System.out.println(message);
    }
}
