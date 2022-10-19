package nuaa.edu.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Classname Resource
 * @Description
 * @Date 2022/10/14 20:20
 * @Created by brain
 */
public interface Resource {

    InputStream getInputStream() throws IOException;
}
