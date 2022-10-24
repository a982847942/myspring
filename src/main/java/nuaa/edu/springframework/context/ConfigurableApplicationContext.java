package nuaa.edu.springframework.context;

import nuaa.edu.springframework.beans.BeansException;

/**
 * @Classname ConfigurableApplicationContext
 * @Description
 * @Date 2022/10/19 15:23
 * @Created by brain
 */
public interface ConfigurableApplicationContext extends ApplicationContext{
    /**
     * 刷新容器
     *
     * @throws BeansException
     */
    void refresh() throws BeansException;
    void registerShutdownHook();

    void close();
}
