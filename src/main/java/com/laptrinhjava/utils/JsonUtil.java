package com.laptrinhjava.utils;

import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	public static <T> T toModel(BufferedReader reader, Class<T> clazz) {
		StringBuilder builder = new StringBuilder();
		String line = "";
		try {
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
			return new ObjectMapper().readValue(builder.toString(), clazz);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
