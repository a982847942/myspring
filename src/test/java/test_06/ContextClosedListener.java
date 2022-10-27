package test_06;

import nuaa.edu.springframework.context.ApplicationListener;
import nuaa.edu.springframework.context.event.ContextClosedEvent;

/**
 * @Classname ContextClosedListener
 * @Description
 * @Date 2022/10/22 10:28
 * @Created by brain
 */
public class ContextClosedListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("监听到容器要关闭了，赶紧完成清理工作！");
    }
}
