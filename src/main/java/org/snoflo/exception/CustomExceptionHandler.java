package org.snoflo.exception;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;

public class CustomExceptionHandler implements InvocationHandler {

    private final Object target;

    public CustomExceptionHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            return method.invoke(target, args);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause(); // 원래 발생한 예외를 가져옴
            if (cause instanceof SQLException) {
                System.err.println("테이블 생성에 실패했습니다: " + cause.getMessage());
                throw new TableCreationException("테이블 생성 실패", cause);
            }
            throw cause; // 그 외 다른 예외는 그대로 던짐
        }
    }

}
