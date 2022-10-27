package nuaa.edu.springframework.context;

/**
 * @Classname ApplicationEventPublisher
 * @Description
 * @Date 2022/10/22 9:42
 * @Created by brain
 */
public interface ApplicationEventPublisher {
    /**
     * Notify all listeners registered with this application of an application
     * event. Events may be framework events (such as RequestHandledEvent)
     * or application-specific events.
     * @param event the event to publish
     */
    void publishEvent(ApplicationEvent event);
}
