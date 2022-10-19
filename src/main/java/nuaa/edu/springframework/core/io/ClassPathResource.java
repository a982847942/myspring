package nuaa.edu.springframework.core.io;

import nuaa.edu.springframework.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Classname ClassPathResource
 * @Description
 * @Date 2022/10/14 20:21
 * @Created by brain
 */
public class ClassPathResource implements Resource{
    private final String path;
    private ClassLoader classLoader;


    public ClassPathResource(String path) {
        this(path,null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        this.path = path;
        this.classLoader = (classLoader == null ?  ClassUtils.getDefaultClassLoader() : classLoader);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream resourceAsStream = classLoader.getResourceAsStream(path);
        if (resourceAsStream == null) {
            throw new FileNotFoundException( this.path +
                    " cannot be opened because it does not exist");
        }
        return resourceAsStream;
    }

    public String getPath() {
        return path;
    }
}
