package nio;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author zjh
 * @Date 2019/07/22,11:41
 */
public class RedisClient {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Proxy target = new Proxy();
        Method method = Proxy.class.getDeclaredMethod("run");
        method.invoke(target);
    }


}

class Proxy {
    public void run() {
        System.out.println("run");
    }
}