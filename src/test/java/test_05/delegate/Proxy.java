package test_05.delegate;

/**
 * @Classname Proxy
 * @Description
 * @Date 2022/10/21 18:57
 * @Created by brain
 */
public class Proxy implements Subject{
    //面向接口编程便于扩展
    Subject subject;

    public Proxy(Subject subject) {
        this.subject = subject;
    }

    //    RealSubject realSubject  //这样面向具体类编程 不便于扩展
    void before(){
        System.out.println("before");
    }
    void after(){
        System.out.println("after");
    }

    @Override
    public void service() {
        before();
        this.subject.service();
        after();
    }
}
