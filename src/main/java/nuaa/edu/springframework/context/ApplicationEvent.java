package nuaa.edu.springframework.context;

import java.util.EventObject;

/**
 * @Classname ApplicationEvent
 * @Description
 * @Date 2022/10/22 9:28
 * @Created by brain
 */
public abstract class ApplicationEvent extends EventObject{
    /*
    Event就是一个事件源
    发布订阅模式下的角色：pub sub center(控制中心 联系pub和sub 使得pub sub完全解耦)
    pub可以发布事件(Event)事件有事件源(EventObject) 事件可以包装事件源(source)
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
