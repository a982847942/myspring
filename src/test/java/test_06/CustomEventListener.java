package test_06;

import nuaa.edu.springframework.context.ApplicationListener;

import java.util.Date;

/**
 * @Classname CustomEventListener
 * @Description
 * @Date 2022/10/22 10:20
 * @Created by brain
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        User source = (User) event.getSource();

        System.out.println("收到：" + event.getSource() + "消息;时间：" + new Date());
        System.out.println("消息：" + event.getId() + ":" + event.getMessage());
        source.receiveMessage("欢迎新用户，开始发送推荐信息!!");
    }
}
