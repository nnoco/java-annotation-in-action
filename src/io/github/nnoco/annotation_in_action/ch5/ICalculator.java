// ICalculator.java
package io.github.nnoco.annotation_in_action.ch5;

import io.github.nnoco.annotation_in_action.ch4.Ignore;
import io.github.nnoco.annotation_in_action.ch4.Log;

/**
 * @author nnoco
 * @since 2020. 05. 17
 */
public interface ICalculator {
    long plus(int operand1, int operand2);

    // 애너테이션은 인터페이스의 메서드에 태그합니다.
    @Log
    long minus(int operand1, @Ignore int operand2);
}
