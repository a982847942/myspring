package test_05.delegate;

/**
 * @Classname RealSubject
 * @Description
 * @Date 2022/10/21 18:55
 * @Created by brain
 */
public class RealSubject implements Subject{
    @Override
    public void service() {
        System.out.println("i'm the really object");
    }
}
