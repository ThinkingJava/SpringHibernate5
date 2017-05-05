package com.ych.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Administrator on 2017-05-03.
 */
public class GenericsUtils {
    /**
     * 获取泛型的类型
     * @param clazz
     * @return Class
     */
    @SuppressWarnings("unchecked")
    public static Class getGenericType(Class clazz){

        Type genType = clazz.getGenericSuperclass();//得到泛型父类
        Type[] types = ((ParameterizedType) genType).getActualTypeArguments();
        if (!(types[0] instanceof Class)) {
            return Object.class;
        }
        return (Class) types[0];

//		ParameterizedType type = (ParameterizedType) clazz.getGenericSuperclass();
//		Class entityClass = (Class) type.getActualTypeArguments()[0];
//		return entityClass;
    }
    /**
     * 获取对象的类名称
     * @param clazz
     * @return 类名称
     */
    @SuppressWarnings("unchecked")
    public static String getGenericName(Class clazz){
        return clazz.getSimpleName();
    }
}
