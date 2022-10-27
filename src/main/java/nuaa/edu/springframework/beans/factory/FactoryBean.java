package nuaa.edu.springframework.beans.factory;

/**
 * @Classname FactoryBean
 * @Description
 * @Date 2022/10/21 14:29
 * @Created by brain
 */
public interface FactoryBean<T> {
    T getObject() throws Exception;

    Class<?> getObjectType();

    boolean isSingleton();
}
