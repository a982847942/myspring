package nuaa.edu.springframework.beans.factory;

/**
 * @Classname IntilizatingBean
 * @Description
 * @Date 2022/10/21 9:36
 * @Created by brain
 */
public interface InitializingBean {
    /**
     * Bean 处理了属性填充后调用
     *
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;
}
