package nuaa.edu.springframework.util;

/**
 * @Classname ClassUtils
 * @Description
 * @Date 2022/10/14 20:28
 * @Created by brain
 */
public class ClassUtils {
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader c1 = null;
        try {
            c1 = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
            // Cannot access thread context ClassLoader - falling back to system class loader...
        }
        if (c1 == null) {
            c1 = ClassUtils.class.getClassLoader();
        }
        return c1;
    }

    /**
     * Check whether the specified class is a CGLIB-generated class.
     * @param clazz the class to check
     */
    public static boolean isCglibProxyClass(Class<?> clazz) {
        return (clazz != null && isCglibProxyClassName(clazz.getName()));
    }

    /**
     * Check whether the specified class name is a CGLIB-generated class.
     * @param className the class name to check
     */
    public static boolean isCglibProxyClassName(String className) {
        return (className != null && className.contains("$$"));
    }
}
