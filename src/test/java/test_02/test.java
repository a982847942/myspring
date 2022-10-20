package test_02;

import cn.hutool.core.io.IoUtil;
import nuaa.edu.springframework.beans.factory.config.BeanDefinitionRegistry;
import nuaa.edu.springframework.beans.factory.config.DefaultListableBeanFactory;
import nuaa.edu.springframework.beans.factory.xml.XMLBeanDefinitionReader;
import nuaa.edu.springframework.core.io.DefaultResourceLoader;
import nuaa.edu.springframework.core.io.Resource;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Classname test
 * @Description
 * @Date 2022/10/14 21:04
 * @Created by brain
 */
public class test {
    private DefaultResourceLoader resourceLoader;
    @Before
    public void init() {
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void test01() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.read(inputStream,"utf-8");
        System.out.println(content);
    }

    @Test
    public void test_file() throws IOException {
        Resource resource = resourceLoader.getResource("src/main/resources/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.read(inputStream,"utf-8");
        System.out.println(content);
    }

    @Test
    public void test_url() throws IOException {
        // 网络原因可能导致GitHub不能读取，可以放到自己的Gitee仓库。读取后可以从内容中搜索关键字；OLpj9823dZ
        Resource resource = resourceLoader.getResource("https://github.com/fuzhengwei/small-spring/blob/main/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.read(inputStream,"utf-8");
        System.out.println(content);
    }

    @Test
    public void test_xml(){
        //todo
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XMLBeanDefinitionReader xmlBeanDefinitionReader = new XMLBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinition("classpath:spring.xml");
//        UserService userService = (UserService)beanFactory.getBean("userService");
        /**
         * Q：1.第一次调用不传入args 第二次传入也没办法填充这些属性
         * 2.如果构造器有两个参数类型相同的怎么办？（这里在Java中属于重载 重载不看参数名 看的是类型，因此考虑现实
         * 情况不应该出现这种问题。但确实可以认为构成）
         * eg:ctr(String name,Integer id)
         *     call传入 name id 可以 传入 address id可以 但意义变了
         */
        UserService userService = (UserService)beanFactory.getBean("userService","哈哈",20);
//        userService.queryInfo();
        userService.queryInfo2();
//        UserService userService2 = (UserService)beanFactory.getBean("userService");
//        userService.queryInfo();
//        userService2.queryInfo2();
    }
}
