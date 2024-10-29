package org.snoflo.factory;

import java.util.HashMap;
import java.util.Map;

public class SimpleIoCContainer {

    private Map<Class<?>, Object> instances = new HashMap<>();

    // 싱글톤 인스턴스 등록
    public <T> void registerSingleton(Class<T> clazz, T instance) {
        instances.put(clazz, instance);
    }

    // 클래스를 등록하고 나중에 인스턴스화
    public <T> void registerProtoType(Class<T> clazz) {
        try {
            instances.put(clazz, clazz.getDeclaredConstructor().newInstance());
        } catch (Exception e) {
            throw new RuntimeException("클래스 인스턴스화 실패: " + clazz.getName(), e);
        }
    }

    // 클래스의 인스턴스 반환
    public <T> T resolve(Class<T> clazz) {
        return clazz.cast(instances.get(clazz));
    }
}
