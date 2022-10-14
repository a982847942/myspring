package nuaa.edu.springframework.beans;

/**
 * @Classname BeanException
 * @Description
 * @Date 2022/10/13 15:27
 * @Created by brain
 */
public class BeansException extends RuntimeException{
    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg,cause);
    }

}
