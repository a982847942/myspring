package nuaa.edu.springframework.beans.factory.config;

/**
 * @Classname BeanReference
 * @Description
 * @Date 2022/10/13 19:43
 * @Created by brain
 */
public class BeanReference {
    private String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
