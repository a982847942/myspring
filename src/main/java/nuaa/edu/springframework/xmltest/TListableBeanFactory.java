package nuaa.edu.springframework.xmltest;

import nuaa.edu.springframework.beans.BeansException;
import nuaa.edu.springframework.beans.factory.BeanFactory;

import java.util.Map;

/**
 * @Classname ListableBeanFactory
 * @Description
 * @Date 2022/10/20 21:35
 * @Created by brain
 */
public interface TListableBeanFactory extends BeanFactory {
    <T> Map<String,T> getBeanOfType(Class<T> type) throws BeansException;
    String[] getBeanDefinitionNames();
}
