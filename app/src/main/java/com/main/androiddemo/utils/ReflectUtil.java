package com.main.androiddemo.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * <br/> Description:反射相关的工具类
 * <br/> Author: xiaojianfeng
 * <br/> Version: 1.0
 * <br/> Date: 2016/8/28 0028
 */
public class ReflectUtil {

    public static Type[] getType(Class<?> clazz) {
        Type[] types = null;
        if (clazz != null) {
            Type type = clazz.getGenericSuperclass();
            ParameterizedType parameterizedType = (ParameterizedType) type;
            types = parameterizedType.getActualTypeArguments();
        }
        return types;
    }

    public static Type getType(Class<?> clazz, int index) {
        Type type = null;
        Type[] types = getType(clazz);
        if (types != null && index >= 0 && types.length > index) {
            type = types[index];
        }
        return type;
    }
}
