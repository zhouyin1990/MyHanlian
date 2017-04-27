package com.xinbo.utils;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class GsonUtils {
	private static Gson sExposeGson;
	static {
		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithModifiers(
			Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC);
		builder.excludeFieldsWithoutExposeAnnotation();
		sExposeGson = builder.create();
	}

	public static <T> T parseJSON(String json, Class<T> clazz) {
		// Gson gson = new Gson();
		T info = sExposeGson.fromJson(json, clazz);
		return info;
	}

	/**
	 * Type type = new TypeToken&lt;ArrayList&lt;TypeInfo>>(){}.getType(); <br>
	 * Type所在的包：java.lang.reflect <br>
	 * TypeToken所在的包：com.google.gson.reflect.TypeToken
	 * 
	 * @param json
	 * @param type
	 * @return
	 */
	public static <T> T parseJSONArray(String jsonArr, Type type) {
		// Gson gson = new Gson();
		T infos = sExposeGson.fromJson(jsonArr, type);
		return infos;
	}

	private GsonUtils() {
	}
}
