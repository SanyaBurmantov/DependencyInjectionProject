package com.burmantov.di.core;
import com.burmantov.di.annotations.Bean;

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public final class InjectorImpl implements Injector{
    private InjectorImpl(){
    }
    private static Map<Class<?>, Object> Container = new HashMap<>();


    public static void inject(String path)  {
        try{
            ReflectPermission reflect = new ReflectPermission(path);
            Set<Class<?>> typesAnnotationWith = (Set<Class<?>>) reflect.getClass().getAnnotation(Bean.class);
            for (Class<?> aClass : typesAnnotationWith){
                Constructor<?> constructor = aClass.getConstructor(null);
                Object bean = constructor.newInstance();
                Class<?>[] interfaces = aClass.getInterfaces();
                for (Class<?> anInterface: interfaces){
                    Container.put(anInterface, bean);
                }
            }
        } catch (Throwable e){
            e.printStackTrace();
            throw new Error("FUUUUUUUUUUUCK");
        }

    }
    @SuppressWarnings("unchecked")
    @Override
    public <T> Provider<T> getProvider(Class<T> type) {
        T bean = (T) Container.get(type);
        if(Objects.isNull(bean)) throw new Error("Not impl found off " + type.getName());
        return (Provider<T>) bean;
    }

    @Override
    public <T> void bind(Class<T> intf, Class<? extends T> impl) {

    }

    @Override
    public <T> void bindSingleton(Class<T> intf, Class<? extends T> impl) {

    }

}
