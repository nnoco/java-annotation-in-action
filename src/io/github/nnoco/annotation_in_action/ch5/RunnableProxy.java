// RunnableProxy.java
package io.github.nnoco.annotation_in_action.ch5;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author nnoco
 * @since 2020. 05. 17
 */
public class RunnableProxy {
    public static void main(String[] args) {
        Runnable runnable = (Runnable) Proxy.newProxyInstance(
                Runnable.class.getClassLoader(),
                new Class[] { Runnable.class },
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println(method.getName() + " 메서드 호출");
                        return null;
                    }
                });

        runnable.run();
    }
}
