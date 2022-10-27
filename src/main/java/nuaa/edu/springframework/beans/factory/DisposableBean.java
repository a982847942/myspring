package nuaa.edu.springframework.beans.factory;

/**
 * @Classname DispoableBean
 * @Description
 * @Date 2022/10/21 9:37
 * @Created by brain
 */
public interface DisposableBean {
    void destroy() throws Exception;
}
