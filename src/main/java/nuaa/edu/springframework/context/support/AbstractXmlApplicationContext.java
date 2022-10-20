package nuaa.edu.springframework.context.support;

import nuaa.edu.springframework.beans.factory.config.DefaultListableBeanFactory;
import nuaa.edu.springframework.beans.factory.support.AbstractRefreshableApplicationContext;
import nuaa.edu.springframework.beans.factory.xml.XMLBeanDefinitionReader;

/**
 * @Classname AbstractXmlApplicationContext
 * @Description
 * @Date 2022/10/19 15:54
 * @Created by brain
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XMLBeanDefinitionReader beanDefinitionReader = new  XMLBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (null != configLocations){
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
