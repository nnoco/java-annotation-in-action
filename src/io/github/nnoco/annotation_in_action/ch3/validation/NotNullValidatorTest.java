// io.github.nnoco.annotation_in_action.ch3.validation.NotNullValidatorTest.java
package io.github.nnoco.annotation_in_action.ch3.validation;

/**
 * NotNullValidator 동작 확인
 */
public class NotNullValidatorTest {
    public static void main(String[] args) throws IllegalAccessException {
        NotNullValidator validator = new NotNullValidator();

        Book book = new Book("Java Annotation in Action", "nnoco", 0);

        // 유효한 객체이므로 통과
        validator.validate(book);

        book.setTitle(null);
        // title이 null이므로 예외 발생
        validator.validate(book);
    }
}
