package test_07;

import nuaa.edu.springframework.aop.AdvisedSupport;
import nuaa.edu.springframework.aop.JdkDynamicAopProxy;
import nuaa.edu.springframework.aop.TargetSource;
import nuaa.edu.springframework.aop.aspectj.AspectJExpressionPointcut;
import nuaa.edu.springframework.aop.framework.Cglib2AopProxy;
import nuaa.edu.springframework.context.support.ClassPathXmlApplicationContext;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Classname test
 * @Description
 * @Date 2022/10/27 18:23
 * @Created by brain
 */
public class test {

//    @Test
//    public void test_proxy_method() {
//        // 目标对象(可以替换成任何的目标对象)
//        Object targetObj = new UserService();
//        // AOP 代理
//        IUserService proxy = (IUserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), targetObj.getClass().getInterfaces(), new InvocationHandler() {
//            // 方法匹配器
//            MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* cn.bugstack.springframework.test.bean.IUserService.*(..))");
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                if (methodMatcher.matches(method, targetObj.getClass())) {
//                    // 方法拦截器
//                    MethodInterceptor methodInterceptor = invocation -> {
//                        long start = System.currentTimeMillis();
//                        try {
//                            return invocation.proceed();
//                        } finally {
//                            System.out.println("监控 - Begin By AOP");
//                            System.out.println("方法名称：" + invocation.getMethod().getName());
//                            System.out.println("方法耗时：" + (System.currentTimeMillis() - start) + "ms");
//                            System.out.println("监控 - End\r\n");
//                        }
//                    };
//                    // 反射调用
//                    return methodInterceptor.invoke(new ReflectiveMethodInvocation(targetObj, method, args));
//                }
//                return method.invoke(targetObj, args);
//            }
//        });
//        String result = proxy.queryUserInfo();
//        System.out.println("测试结果：" + result);
//    }

    /**
     * execution(<修饰符模式>?<返回类型模式><方法名模式>(<参数模式>)<异常模式>?)
     * 除了返回类型模式、方法名模式和参数模式外，其它项都是可选的。
     * execution(public * com.sample.service.impl..*.*(..))
     * 第一个”*“符号 表示返回值的类型任意；
     * com.sample.service.impl	AOP所切的服务的包名，即，我们的业务部分
     * 包名后面的”..“	表示当前包及子包
     * 第二个”*“	表示类名，*即所有类。此处可以自定义
     * .*(..)	表示任何方法名，括号表示参数，两个点表示任何参数类型
     */
    @Test
    public void test_dynamic() {
        // 目标对象
        IUserService userService = new UserService();

        // 组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* test_07.IUserService.*(..))"));

        // 代理对象(JdkDynamicAopProxy)
        IUserService proxy_jdk = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        // 测试调用
        System.out.println("测试结果：" + proxy_jdk.queryInfo());

        // 代理对象(Cglib2AopProxy)
        IUserService proxy_cglib = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();
        // 测试调用
        System.out.println("测试结果：" + proxy_cglib.register("花花"));
    }

    @Test
    public void test_aop() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:test07.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("测试结果：" + userService.queryInfo());
    }

}
