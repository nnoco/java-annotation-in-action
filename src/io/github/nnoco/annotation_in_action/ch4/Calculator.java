// Calculator.java
package io.github.nnoco.annotation_in_action.ch4;

/**
 * @author nnoco
 * @since 2020. 5. 16
 */
public class Calculator {
    public long plus(int operand1, int operand2) {
        return operand1 + operand2;
    }

    @Log
    public long minus(int operand1, @Ignore int operand2) {
        return operand1 - operand2;
    }
}
