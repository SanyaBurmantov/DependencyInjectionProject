package com.burmantov.di.core;
import com.burmantov.di.annotations.Bean;
import com.burmantov.di.annotations.Inject;

import java.lang.reflect.*;
import java.util.*;



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
            injectAnnotationBeanProcessor();
        } catch (Throwable e){
            e.printStackTrace();
            throw new Error("FUUUUUUUUUUUCK");
        }

    }

    private static void injectAnnotationBeanProcessor() throws IllegalAccessException {
        Collection<Object> beans = Container.values();
        for (Object bean : beans){
            Field[] declaredFields = bean.getClass().getDeclaredFields();
            for (Field field : declaredFields){
                if(Objects.nonNull(field.getAnnotation(Inject.class))){
                    field.setAccessible(true);
                    Class<?> interfaceOfImpl = field.getType();
                    Object dependency = getByInterface(interfaceOfImpl);
                    field.set(bean, dependency);
                }
            }
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

    @SuppressWarnings("unchecked")
    public static <T> T getByInterface(Class<T> someInterface) {
        T bean = (T) Container.get(someInterface);
        if (Objects.isNull(bean))
            throw new Error("Не але" + someInterface.getName());
        return bean;
    }
}


