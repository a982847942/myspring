package nuaa.edu.springframework.context.event;

import nuaa.edu.springframework.context.ApplicationEvent;

/**
 * @Classname ContextClosedEvent
 * @Description
 * @Date 2022/10/22 9:34
 * @Created by brain
 */
public class ContextClosedEvent extends ApplicationContextEvent {
    public ContextClosedEvent(Object source) {
        super(source);
    }
}
