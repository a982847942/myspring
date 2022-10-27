package nuaa.edu.springframework.beans.factory;

/**
 * @Classname BeanClassloaderAware
 * @Description
 * @Date 2022/10/21 13:14
 * @Created by brain
 */
public interface BeanClassloaderAware extends Aware{
    void setBeanClassLoader(ClassLoader classLoader);
}
