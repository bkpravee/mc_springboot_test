/**
 * 
 */
package com.mc.springboot.service;

/**
 * @author praveen
 *
 */
public interface ICityNavigationService {
	
	/**
	 * this method is used to load initial data from flat file in the classpath on the application startup
	 * 
	 * @throws Exception
	 */
	public void load() throws Exception;
	/**
	 *  This method will determine if 2 cities are connected if origin and destination are provided
	 *  
	 * @param start
	 * @param destination
	 * @return
	 * @throws Exception
	 */
	public boolean isConnected(String start, String destination) throws Exception;
}
