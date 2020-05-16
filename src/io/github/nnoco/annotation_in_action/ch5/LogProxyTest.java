// LogProxyTest.java
package io.github.nnoco.annotation_in_action.ch5;

import java.lang.reflect.Proxy;

/**
 * @author nnoco
 * @since 2020. 05. 17
 */
public class LogProxyTest {
    public static void main(String[] args) {
        ICalculator calculator = (ICalculator) Proxy.newProxyInstance(
                Calculator.class.getClassLoader(),
                new Class[] { ICalculator.class },
                new LogProxy(new Calculator()));

        calculator.minus(1, 2);
    }
}
