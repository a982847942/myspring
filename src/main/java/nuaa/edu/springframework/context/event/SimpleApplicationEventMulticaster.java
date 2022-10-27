package nuaa.edu.springframework.context.event;

import nuaa.edu.springframework.beans.factory.BeanFactory;
import nuaa.edu.springframework.context.ApplicationEvent;
import nuaa.edu.springframework.context.ApplicationListener;

/**
 * @Classname SimpleApplicationEventMulticaster
 * @Description
 * @Date 2022/10/22 9:45
 * @Created by brain
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster{
    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void multicastEvent(final ApplicationEvent event) {
        //获取所有对该事件感兴趣的sub 然后回调sub的方法
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
