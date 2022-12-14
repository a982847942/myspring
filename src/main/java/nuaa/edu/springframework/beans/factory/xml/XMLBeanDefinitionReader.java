package nuaa.edu.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.sun.javaws.jnl.XMLUtils;
import nuaa.edu.springframework.beans.BeansException;
import nuaa.edu.springframework.beans.PropertyValue;
import nuaa.edu.springframework.beans.factory.config.BeanDefinitionRegistry;
import nuaa.edu.springframework.beans.factory.config.BeanReference;
import nuaa.edu.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import nuaa.edu.springframework.beans.factory.support.BeanDefinition;
import nuaa.edu.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import nuaa.edu.springframework.core.io.Resource;
import nuaa.edu.springframework.core.io.ResourceLoader;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.dom4j.Document;
import org.dom4j.Element;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Classname XMLBeanDefinitionReader
 * @Description
 * @Date 2022/10/14 20:49
 * @Created by brain
 */
public class XMLBeanDefinitionReader extends AbstractBeanDefinitionReader {
    public XMLBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XMLBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinition(Resource resource) throws BeansException {
        InputStream inputStream = null;
        try {
            inputStream = resource.getInputStream();
            if (inputStream != null) {
                doLoadBeanDefinitions(inputStream);
            }
        } catch (IOException | ClassNotFoundException | DocumentException e) {
            throw new BeansException("IOException parsing XML document from " + resource, e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void loadBeanDefinition(Resource... resources) throws BeansException {
//        for (Resource resource : resources) {
//            InputStream inputStream = null;
//            try {
//                inputStream = resource.getInputStream();
//                if (inputStream != null) {
//                    doLoadBeanDefinitions(inputStream);
//                }
//            } catch (IOException | ClassNotFoundException e) {
//                throw new BeansException("IOException parsing XML document from " + resource, e);
//            } finally {
//                try {
//                    inputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        for (Resource resource : resources) {
            loadBeanDefinition(resource);
        }
    }

    @Override
    public void loadBeanDefinition(String locations) throws BeansException {
        Resource resource = getResourceLoader().getResource(locations);
        loadBeanDefinition(resource);
    }

    @Override
    public void loadBeanDefinitions(String[] locations) throws BeansException {
        for (String location : locations) {
            loadBeanDefinition(location);
        }
    }

    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException, DocumentException {
//        Document doc = XmlUtil.readXML(inputStream);
//        Element root = doc.getDocumentElement();
//        NodeList childNodes = root.getChildNodes();


        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        org.dom4j.Element root = document.getRootElement();

        // ?????? context:component-scan ??????????????????????????????????????????????????????????????? BeanDefinition
        Element componentScan = root.element("component-scan");
        if (null != componentScan) {
            String scanPath = componentScan.attributeValue("base-package");
            if (StrUtil.isEmpty(scanPath)) {
                throw new BeansException("The value of base-package attribute can not be empty or null");
            }
            scanPackage(scanPath);
        }



        List<Element> beanList = root.elements("bean");
        for (Element bean : beanList) {
            //??????beanDefinition?????????
            String id = bean.attributeValue("id");
            String name = bean.attributeValue("name");
            String className = bean.attributeValue("class");
            String initMethod = bean.attributeValue("init-method");
            String destroyMethodName = bean.attributeValue("destroy-method");
            String beanScope = bean.attributeValue("scope");

            // ?????? Class??????????????????????????????
            Class<?> clazz = Class.forName(className);
            // ????????? id > name
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            // ??????Bean
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            beanDefinition.setInitMethodName(initMethod);
            beanDefinition.setDestroyMethodName(destroyMethodName);

            if (StrUtil.isNotEmpty(beanScope)) {
                beanDefinition.setScope(beanScope);
            }

            List<Element> propertyList = bean.elements("property");
            // ??????bean??????????????????
            for (Element property : propertyList) {
                // ???????????????property
                String attrName = property.attributeValue("name");
                String attrValue = property.attributeValue("value");
                String attrRef = property.attributeValue("ref");
                // ??????????????????????????????????????????
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
                // ??????????????????
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            if (getRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }
            // ?????? BeanDefinition
            getRegistry().registryBeanDefinition(beanName, beanDefinition);
        }



//        for (int i = 0; i < childNodes.getLength(); i++) {
//            // ????????????
//            if (!(childNodes.item(i) instanceof Element)) continue;
//            // ????????????
//            if (!"bean".equals(childNodes.item(i).getNodeName())) continue;
//
//            // ????????????
//            Element bean = (Element) childNodes.item(i);
//            String id = bean.getAttribute("id");
//            String name = bean.getAttribute("name");
//            String className = bean.getAttribute("class");
//
//            //???init-method???destroy-method?????????
//            String initMethod = bean.getAttribute("init-method");
//            String destroyMethodName = bean.getAttribute("destroy-method");
//            String beanScope = bean.getAttribute("scope");
//
//            // ?????? Class??????????????????????????????
//            Class<?> clazz = Class.forName(className);
//            // ????????? id > name
//            String beanName = StrUtil.isNotEmpty(id) ? id : name;
//            if (StrUtil.isEmpty(beanName)) {
//                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
//            }
//
//            // ??????Bean
//            BeanDefinition beanDefinition = new BeanDefinition(clazz);
//            //???????????????beanDefinition???
//            beanDefinition.setInitMethodName(initMethod);
//            beanDefinition.setDestroyMethodName(destroyMethodName);
//            if (StrUtil.isNotEmpty(beanScope)) {
//                beanDefinition.setScope(beanScope);
//            }
//            // ?????????????????????
//            for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
//                if (!(bean.getChildNodes().item(j) instanceof Element)) continue;
//                if (!"property".equals(bean.getChildNodes().item(j).getNodeName())) continue;
//                // ???????????????property
//                Element property = (Element) bean.getChildNodes().item(j);
//                String attrName = property.getAttribute("name");
//                String attrValue = property.getAttribute("value");
//                String attrRef = property.getAttribute("ref");
//                // ??????????????????????????????????????????
//                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
//                // ??????????????????
//                PropertyValue propertyValue = new PropertyValue(attrName, value);
//                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
//            }
//            if (getRegistry().containsBeanDefinition(beanName)) {
//                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
//            }
//            // ?????? BeanDefinition
//            getRegistry().registryBeanDefinition(beanName, beanDefinition);
//        }
    }
    private void scanPackage(String scanPath) {
        String[] basePackages = StrUtil.splitToArray(scanPath, ',');
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(getRegistry());
        scanner.doScan(basePackages);
    }
}
