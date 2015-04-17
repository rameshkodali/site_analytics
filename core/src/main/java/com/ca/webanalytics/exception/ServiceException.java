package com.ca.webanalytics.exception;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

public class ServiceException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static XLogger LOGGER = XLoggerFactory.getXLogger(ServiceException.class.getName());
	
	
	public ServiceException(Throwable e)
	{
		LOGGER.error("Error Message is : {}",e.getMessage());
	}
	
	public ServiceException(Throwable e,String errorMessage)
	{
		LOGGER.error("Error Message is : {}",errorMessage);
	}
}
