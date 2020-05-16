package io.github.nnoco.annotation_in_action.ch5;

import io.github.nnoco.annotation_in_action.ch4.Ignore;
import io.github.nnoco.annotation_in_action.ch4.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author nnoco
 * @since 2020. 05. 17
 */
public class LogProxy implements InvocationHandler {
    private Object object;

    public static Object newInstance(Object object, Class<?>... interfaces) {
        return Proxy.newProxyInstance(
                object.getClass().getClassLoader(),
                interfaces,
                new LogProxy(object));
    }

    public LogProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        boolean logAnnotationPresented = method.isAnnotationPresent(Log.class);

        // Log 애너테이션이 있으면 메서드를 호출하기 전에 파라미터를 로그로 출력
        if(logAnnotationPresented) {
            String parameterLog = getParameterLog(method.getParameters(), args);

            System.out.println(method.getName() + " 메서드 시작. " + parameterLog);
        }

        // 리플렉션을 통해 메서드 실행
        Object result = method.invoke(object, args);

        // Log 애너테이션이 있으면 리턴 값을 출력
        if(logAnnotationPresented) {
            System.out.println(method.getName() + " 메서드 끝. " + result);
        }

        // 값 반환
        return result;
    }

    String getParameterLog(Parameter[] parameters, Object[] arguments) {
        return IntStream.range(0, parameters.length)
                .boxed()
                // Ignore 애너테이션이 태그되지 않은 파라미터만 필터합니다.
                .filter(i -> !parameters[i].isAnnotationPresent(Ignore.class))

                // "파라미터 이름=인자"를 반환합니다.
                // 바이트코드에 파라미터 이름이 유지되어야 파라미터 이름을 얻을 수 있고,
                // 그렇지 않은 경우 argN 형식의 이름을 반환합니다.
                .map(i -> parameters[i].getName() + "=" + arguments[i])
                .collect(Collectors.joining(", "));
    }
}
