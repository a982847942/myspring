package nuaa.edu.springframework.context;

import nuaa.edu.springframework.beans.BeansException;

/**
 * @Classname ApplicaitionContextAware
 * @Description
 * @Date 2022/10/21 13:15
 * @Created by brain
 */
public interface ApplicationContextAware {
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
