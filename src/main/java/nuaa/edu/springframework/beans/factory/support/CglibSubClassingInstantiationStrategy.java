package nuaa.edu.springframework.beans.factory.support;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import nuaa.edu.springframework.beans.BeansException;

import java.lang.reflect.Constructor;

/**
 * @Classname CglibSubClassingInstantionStrategy
 * @Description
 * @Date 2022/10/13 16:48
 * @Created by brain
 */
public class CglibSubClassingInstantiationStrategy implements InstantiationStrategy{

    @Override
    public Object instantiate(String beanName, BeanDefinition beanDefinition, Constructor ctr, Object... args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        if (null == ctr) return enhancer.create();
        return enhancer.create(ctr.getParameterTypes(), args);
    }
}
