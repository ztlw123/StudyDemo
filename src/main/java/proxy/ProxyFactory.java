package proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

/**
 * @Author zjh
 * @Date 2019/04/26,09:31
 */
public class ProxyFactory implements MethodInterceptor {

    //目标对象
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    //JDK动态代理
    public Object getProxyInstance() {

        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        String methodName = method.getName();
                        Object result = null;
                        if("find".equals(methodName)) {
                            result = method.invoke(target, args);
                        }
                        else {
                            System.out.println("start============");
                            result = method.invoke(target, args);
                            System.out.println("end==============");
                        }

                        return result;
                    }
                });

        return proxy;
    }

    //CGLIB动态代理
    public Object getTargetCGLIBInstance() {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);

        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("处理前===============");
        methodProxy.invokeSuper(o, objects);
        System.out.println("处理后===============");

        return null;
    }

    public static void main(String[] args) {
        IUserDao target = new UserDao();
        ProxyFactory proxyFactory = new ProxyFactory(target);

        IUserDao proxy = (IUserDao) proxyFactory.getProxyInstance();

        System.out.println(target.getClass());
        System.out.println(proxy.getClass());
        proxy.find();
        proxy.save();

        System.out.println("**********************************************");

        ProxyFactory p2 = new ProxyFactory(new NoInterfDao());
        NoInterfDao cglibDao = (NoInterfDao) p2.getTargetCGLIBInstance();
        cglibDao.save();
    }
}
