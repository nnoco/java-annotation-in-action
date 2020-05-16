// CalculatorReflection.java
package io.github.nnoco.annotation_in_action.ch4;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author nnoco
 * @since 2020. 05. 16
 */
public class CalculatorReflection {
    void getPlusMethodBySeveralWays() throws Exception {
        Class<?> clazz = Calculator.class;

        // getMethod(이름, 파라미터 타입 가변인자)로 plus 메서드를 가져옵니다.
        // 해당 클래스에 정의된 public 메서드 또는 상속 받은 public 메서드만 가져올 수 있습니다.
        Method plus1 = clazz.getMethod("plus", int.class, int.class);

        // getMethods()로 모든 메서드 목록을 배열로 가져옵니다.
        // 해당 클래스에 정의된 public 메서드 또는 상속 받은 public 메서드만 가져올 수 있습니다.
        Method[] methods1 = clazz.getMethods();

        // getDeclaredMethod(이름, 파라미터 타입 가변인자)로 plus 메서드를 가져옵니다.
        // 상속 받은 메서드는 해당되지 않으며 해당 클래스에 정의된 메서드만 가져옵니다.
        Method plus2 = clazz.getDeclaredMethod("plus", int.class, int.class);

        // getDeclaredMethods()로 모든 메서드 목록을 배열로 가져옵니다.
        // 상속 받은 메서드는 해당되지 않으며 해당 클래스에 정의된 메서드만 가져옵니다.
        Method[] methods2 = clazz.getDeclaredMethods();
    }

    long calculateWithLog(Calculator calculator, String name, int operand1, int operand2) throws Exception {
        Class<?> clazz = Calculator.class;

        Method minusMethod = clazz.getMethod(name, int.class, int.class);

        boolean logAnnotationPresented = minusMethod.isAnnotationPresent(Log.class);

        // 처리하기 용이하도록 파라미터를 배열로 묶습니다.
        Object[] arguments = { operand1, operand2 };

        // Log 애너테이션이 있으면 메서드를 호출하기 전에 파라미터를 로그로 출력
        if(logAnnotationPresented) {
            String parameterLog = getParameterLog(minusMethod.getParameters(), arguments);

            System.out.println(name + " 메서드 시작. " + parameterLog);
        }

        // 리플렉션을 통해 메서드 실행
        long result = (long) minusMethod.invoke(calculator, arguments);

        // Log 애너테이션이 있으면 리턴 값을 출력
        if(logAnnotationPresented) {
            System.out.println(name + " 메서드 끝. " + result);
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

    public static void main(String[] args) throws Exception {
        Calculator calculator = new Calculator();

        CalculatorReflection calculatorReflection = new CalculatorReflection();

        // plus 메서드는 @Log가 없으므로 로그가 출력되지 않습니다.
        long plus = calculatorReflection.calculateWithLog(calculator, "plus", 1, 2);

        // minus 메서드는 @Log가 있으므로 로그가 출력됩니다.
        long minus = calculatorReflection.calculateWithLog(calculator, "minus", 1, 2);
    }
}
