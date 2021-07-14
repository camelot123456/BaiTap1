package com.laptrinhjava.utils;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class FormUtil {

	public static <T> T toModel(Class<T> clazz, HttpServletRequest req) {
		T obj = null;
		try {
			obj = clazz.newInstance();
			BeanUtils.populate(obj, req.getParameterMap());
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	
}
