/**
 * 
 */
package com.abcplusd.masterapi.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author priyamgupta
 *
 */
public final class JSONUtil {

	private static final Logger LOG = LogManager.getLogger();

	/** This does not allow creation of an instance of a util class */
	private JSONUtil() {
	}

	/**
	 * Converts a jsonString to Object of specified class <br/>
	 * Allows {@code first_name} to {@code firstName} parsing only
	 * 
	 * @param jsonString
	 * @param classz
	 * @return Parsed Class object
	 */
	public static <T> T parseClass(String jsonString, Class<T> clazz) {
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
		return (T) gson.fromJson(jsonString, clazz);
	}

	/**
	 * Converts a jsonString to Object of specified class <br/>
	 * Allows {@code firstName} to {@code firstName} parsing only
	 * 
	 * @param jsonString
	 * @param classz
	 * @return Parsed Class object
	 */
	public static <T> T fromJson(String json, Class<T> clazz) {
		Gson gson = new Gson();
		T obj = gson.fromJson(json, clazz);
		LOG.debug("fromJson - json : " + json);
		return obj;
	}

	/**
	 * Converts given object to JSON String
	 * 
	 * @param obj
	 * @return {@link String}
	 */
	public static String objectToJSON(Object obj) {
		Gson gson = new Gson();
		String json;
		if (obj == null) {
			json = "";
		} else {
			json = gson.toJson(obj);
		}
		LOG.debug("objectToJSON - returned jsonString is <{}>", json);
		return json;
	}
}
