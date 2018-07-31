package com.docker.container.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author atwa  
 * Jul 2, 2018
 */
public class GenericResponse {

	/**
	 * An Map that contains the actual objects
	 */
	private Map<String, Object> mapData =new HashMap<>();

	/**
	 * A String containing error code. Set to 1 if there is an error
	 */
	private int statusCode = 0;

	/**
	 * A String containing error message.
	 */
	private String message;

	/**
	 * A String containing error code.
	 * 
	 * @return the errorCode
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * A String containing error code.
	 * 
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setStatusCode(int errorCode) {
		this.statusCode = errorCode;
	}

	/**
	 * A String containing error message.
	 * 
	 * @return the errorMessage
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * A String containing error message.
	 * 
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setMessage(String errorMessage) {
		this.message = errorMessage;
	}

	/**
	 * @return the mapData
	 */
	public Map<String, Object> getMapData() {
		return mapData;
	}

	/**
	 * @param mapData
	 *            the mapData to set
	 */
	public void setMapData(Map<String, Object> mapData) {
		this.mapData = mapData;
	}

}
