package com.javaweb.util;

import java.util.Map;

public class MapUtils {
	public static <T> T getObject(Map<String, Object> params, String key, Class<T> tClass) {
		Object object = params.getOrDefault(key, null);
		if (object != null) {
			//chuyen doi value phu hop tclass yeu cau
			if (tClass.getTypeName().equals("java.lang.Long")) {
				object = object != "" ? Long.valueOf(object.toString()) : null;
			} else if (tClass.getTypeName().equals("java.lang.Integer")) {
				object = object != "" ? Integer.valueOf(object.toString()) : null;
			} else if (tClass.getTypeName().equals("java.lang.String")) {
				object = object != "" ? object.toString() : null;
			}
			return tClass.cast(object); // cast kieu tra ve
		}
		return null;
	}
	
}
