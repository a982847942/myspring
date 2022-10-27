package test_05.delegate;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;

/**
 * @Classname test
 * @Description
 * @Date 2022/10/21 18:59
 * @Created by brain
 */
public class test {
    @Test
    public void test01(){
        Subject subject = new Proxy(new RealSubject());
        subject.service();
    }

    @Test
    public void test02() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Subject realSubject = new RealSubject();
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        //1.0 获取代理类的类对象，主要设置相同的ClassLoader去加载目标类实现的接口Subject类
        Class<?> proxyClass = java.lang.reflect.Proxy.getProxyClass(test.class.getClassLoader(), new Class[]{Subject.class});
        //2.0 得到代理类后，就可以通过代理类的处理器句柄来得到构造器
        final Constructor<?> con = proxyClass.getConstructor(InvocationHandler.class);
        //3.0 获取具体执行方法的句柄处理器，目的通过构造器传入被代理目标类对象，注入到代理类处理器句柄中进行代理调用
        final InvocationHandler handler = new DynamicProxy(realSubject);
        //4.0 通过构造器创建代理类对象
        Subject subject = (Subject)con.newInstance(handler);

        //5.0 最后调用方法
        subject.service();


        Subject proxy = (Subject)java.lang.reflect.Proxy.newProxyInstance(test.class.getClassLoader(), new Class[]{Subject.class},
                new DynamicProxy(new RealSubject()));
        proxy.service();
    }
}
