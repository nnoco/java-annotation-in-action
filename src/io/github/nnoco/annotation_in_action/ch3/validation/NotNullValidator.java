// io.github.nnoco.annotation_in_action.ch3.validation.NotNullValidator.java
package io.github.nnoco.annotation_in_action.ch3.validation;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * {@link NotNull}이 태그된 필드의 null 여부를 체크하는 유효성 검사기
 */
public class NotNullValidator {
    /**
     * 전달된 객체의 NotNull 유효성을 검증합니다.
     * @param object 유효성을 검사할 객체
     * @throws IllegalArgumentException @NotNull을 태그한 필드의 값이 null인 경우
     * @throws NullPointerException {@code object}가 null인 경우
     */
    public void validate(Object object) {
        if(Objects.isNull(object)) {
            throw new NullPointerException("object는 null일 수 없습니다.");
        }

        // 1. 전달된 객체의 Class 객체 가져오기
        Class<?> clazz = object.getClass();

        // 2. 해당 클래스에 정의된 모든 필드 가져오기
        Field[] fields = clazz.getDeclaredFields();

        for(Field field: fields) {
            // 필드에 태그된 NotNull 애너테이션 가져오기(없으면 null)
            NotNull notNull = field.getAnnotation(NotNull.class);

            // @NotNull 애너테이션이 태그되어 있는 경우
            if(Objects.nonNull(notNull)) {
                validateField(object, field, notNull);
            }
        }
    }

    private void validateField(Object object, Field field, NotNull notNull) {
        // 필드의 값 가져오기
        Object value = getFieldValue(object, field);

        if(Objects.isNull(value)) {
            // 필드의 값이 null이라면 예외 발생
            throw new IllegalArgumentException(notNull.message());
        }
    }

    private Object getFieldValue(Object object, Field field) {
        // private 접근 제어 지시자를 무시하도록 accessible을 true로 설정
        boolean accessible = field.isAccessible();
        field.setAccessible(true);

        // 필드의 값 가져오기
        Object value = null;
        try {
            // object 객체의 field 필드의 값 조회
            value = field.get(object);
        } catch(IllegalAccessException e) {
            // 필드에 접근할 수 없는 경우 발생 - accessible을 변경하여 발생하지 않음
            e.printStackTrace();
        }

        // 원래의 accessible로 설정
        field.setAccessible(accessible);

        return value;
    }
}
