package nuaa.edu.springframework.beans.factory.support;

import nuaa.edu.springframework.beans.BeansException;
import nuaa.edu.springframework.beans.factory.ConfigurableListableBeanFactory;
import nuaa.edu.springframework.beans.factory.config.DefaultListableBeanFactory;
import nuaa.edu.springframework.context.support.AbstractApplicationContext;

/**
 * @Classname AbstractRefreshableApplicationContext
 * @Description
 * @Date 2022/10/19 15:38
 * @Created by brain
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {
    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
