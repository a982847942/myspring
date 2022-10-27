package nuaa.edu.springframework.beans.factory;

/**
 * @Classname BeanNameAware
 * @Description
 * @Date 2022/10/21 13:15
 * @Created by brain
 */
public interface BeanNameAware extends Aware{
    void setBeanName(String name);
}
