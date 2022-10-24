package test_03;

import nuaa.edu.springframework.beans.factory.config.DefaultListableBeanFactory;
import nuaa.edu.springframework.beans.factory.xml.XMLBeanDefinitionReader;
import nuaa.edu.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @Classname xmltest
 * @Description
 * @Date 2022/10/19 16:12
 * @Created by brain
 */
public class test {
    @Test
    public void test_BeanFactoryPostProcessorAndBeanPostProcessor(){
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件&注册Bean
        XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinition("classpath:test03_1.xml");

        // 3. BeanDefinition 加载完成 & Bean实例化之前，修改 BeanDefinition 的属性值
        MyBeanPostFactoryProcessor beanFactoryPostProcessor = new MyBeanPostFactoryProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        // 4. Bean实例化之后，修改 Bean 属性信息
        MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);

        // 5. 获取Bean对象调用方法
        UserService userService = beanFactory.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }

    @Test
    public void test_xml() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:test03_2.xml");

        // 2. 获取Bean对象调用方法
        UserService userService =  applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }

    //Bean的初始化和销毁方法
    @Test
    public void test_xml2() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:test03_2.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }
}
