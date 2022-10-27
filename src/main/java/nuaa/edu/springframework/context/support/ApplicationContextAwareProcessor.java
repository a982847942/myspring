package nuaa.edu.springframework.context.support;

import nuaa.edu.springframework.beans.BeansException;
import nuaa.edu.springframework.beans.factory.config.BeanPostProcessor;
import nuaa.edu.springframework.context.ApplicationContext;
import nuaa.edu.springframework.context.ApplicationContextAware;

/**
 * @Classname ApplicationContextAwareProcessor
 * @Description
 * @Date 2022/10/21 13:16
 * @Created by brain
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {
    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware){
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
