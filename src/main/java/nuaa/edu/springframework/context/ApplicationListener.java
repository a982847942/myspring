package nuaa.edu.springframework.context;

import java.util.EventListener;

/**
 * @Classname ApplicationListener
 * @Description
 * @Date 2022/10/22 9:38
 * @Created by brain
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener  {
    /**
     * Handle an application event.
     * @param event the event to respond to
     */
    void onApplicationEvent(E event);
}
