package test_05.delegate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Classname DynamicProxy
 * @Description
 * @Date 2022/10/21 19:18
 * @Created by brain
 */
public class DynamicProxy implements InvocationHandler {
    private Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before" + method.getName());
        Object res = method.invoke(target, args);
        System.out.println("after" + method.getName());
        return res;
    }
}
