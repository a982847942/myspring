package nuaa.edu.springframework.context.event;

import nuaa.edu.springframework.context.ApplicationContext;
import nuaa.edu.springframework.context.ApplicationEvent;

/**
 * @Classname ApplicaitonContextEvent
 * @Description
 * @Date 2022/10/22 9:32
 * @Created by brain
 */
public class ApplicationContextEvent extends ApplicationEvent{
    public ApplicationContextEvent(Object source) {
        super(source);
    }
    /**
     * Get the <code>ApplicationContext</code> that the event was raised for.
     */
    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
