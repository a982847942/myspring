package nuaa.edu.springframework.core.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Classname FileSystemResource
 * @Description
 * @Date 2022/10/14 20:22
 * @Created by brain
 */
public class FileSystemResource implements Resource{
    final String path;

    public FileSystemResource(String path) {
        this.path = path;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        FileInputStream inputStream = new FileInputStream(path);
        return inputStream;
    }

    public String getPath() {
        return path;
    }
}
