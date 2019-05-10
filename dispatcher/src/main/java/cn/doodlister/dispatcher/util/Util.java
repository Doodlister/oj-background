package cn.doodlister.dispatcher.util;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;


import java.beans.FeatureDescriptor;

import java.io.*;
import java.util.UUID;
import java.util.stream.Stream;

public class Util {
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }

    public static <T> String beanToString(T value) {
        if(value == null)
            return null;
        Class<?> clazz = value.getClass();
        if(clazz == int.class || clazz==Integer.class){
            return ""+value;
        }else if(clazz == String.class ){
            return (String)value;
        }else if(clazz == long.class || clazz==Long.class){
            return ""+value;
        }else {
            return JSON.toJSONString(value);
        }

    }
    public static <T> T stringToBean(String str,Class<T> clazz) {
        if(str == null || clazz == null || str.length()<=0)
            return null;

        if(clazz == int.class || clazz==Integer.class){
            return (T)Integer.valueOf(str);
        }else if(clazz == String.class ){
            return (T)str;
        }else if(clazz == long.class || clazz==Long.class){
            return (T)Long.valueOf(str);
        }else {
            return JSON.toJavaObject(JSON.parseObject(str),clazz);
        }
    }
    public static String generateUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
    public static boolean output(String fileName,String path) throws IOException {
       return false;
    }
}
