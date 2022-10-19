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
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XMLBeanDefinitionReader xmlBeanDefinitionReader = new XMLBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinition("classpath:spring.xml");
        UserService userService = (UserService)beanFactory.getBean("userService");
        userService.queryInfo2();
    }
}
