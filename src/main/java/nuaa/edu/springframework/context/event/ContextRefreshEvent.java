package nuaa.edu.springframework.context.event;

import nuaa.edu.springframework.context.ApplicationEvent;

/**
 * @Classname ContextRefreshEvent
 * @Description
 * @Date 2022/10/22 9:35
 * @Created by brain
 */
public class ContextRefreshEvent extends ApplicationContextEvent {
    public ContextRefreshEvent(Object source) {
        super(source);
    }
}
