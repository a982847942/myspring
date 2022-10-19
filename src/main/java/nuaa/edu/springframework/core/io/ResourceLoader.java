package nuaa.edu.springframework.core.io;

/**
 * @Classname ResourceLoader
 * @Description
 * @Date 2022/10/14 20:22
 * @Created by brain
 */
public interface ResourceLoader {
    /**
     * Pseudo URL prefix for loading from the class path: "classpath:"
     */
    String CLASSPATH_URL_PREFIX = "classpath:";
    Resource getResource(String location);
}
