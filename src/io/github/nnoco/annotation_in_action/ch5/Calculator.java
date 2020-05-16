// Calculator.java
package io.github.nnoco.annotation_in_action.ch5;

/**
 * @author nnoco
 * @since 2020. 5. 16
 */
public class Calculator implements ICalculator {
    public long plus(int operand1, int operand2) {
        return operand1 + operand2;
    }

    public long minus(int operand1, int operand2) {
        return operand1 - operand2;
    }
}
