package nuaa.edu.springframework.context.event;

import nuaa.edu.springframework.context.ApplicationEvent;
import nuaa.edu.springframework.context.ApplicationListener;

/**
 * @Classname ApplicationEventMulticaster
 * @Description
 * @Date 2022/10/22 9:36
 * @Created by brain
 */
public interface ApplicationEventMulticaster {
    /**
     * Add a listener to be notified of all events.
     * @param listener the listener to add
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * Remove a listener from the notification list.
     * @param listener the listener to remove
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * Multicast the given application event to appropriate listeners.
     * @param event the event to multicast
     */
    void multicastEvent(ApplicationEvent event);
}
