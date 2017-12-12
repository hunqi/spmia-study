package com.thoughtmechanix.organization.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
    
	private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
	private static final String EMPTY_JSON = "{}";
	private static final ObjectMapper mapper = new ObjectMapper();
	
	static {
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
	}
	
	private JsonUtils() {}
	
	public static String format(Object pojo){
		try {
			return mapper.writeValueAsString(pojo);
		} catch (JsonProcessingException e) {
			logger.error("JsonProcessingException, pojo = {}", pojo, e);
			return EMPTY_JSON;
		}
	}
	
	public static <T> T parse(String json, Class<T> clazz) {
		try {
			return mapper.readValue(json, clazz);
		} catch (IOException e) {
			logger.error("IOException, json = {}, clazz = {}", json, clazz, e);
			try {
				return clazz.newInstance();
			} catch (InstantiationException | IllegalAccessException e1) {
				logger.error("InstantiationException or IllegalAccessException, clazz = {}", clazz, e1);
				return null;
			}
		}
	}
	
	public static <T> List<T> parseToList(String json, Class<T> clazz) {
		try {
			return mapper.readValue(json, mapper.getTypeFactory().constructParametricType(ArrayList.class, clazz));
		} catch (IOException e) {
			logger.error("IOException, json = {}, clazz = {}", json, clazz, e);
			return null;
		}
	}
	
	public static boolean isEmptyJson(String json) {
		return EMPTY_JSON.equals(json);
	}
}
