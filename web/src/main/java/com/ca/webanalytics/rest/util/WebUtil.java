package com.ca.webanalytics.rest.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.transform.stream.StreamSource;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.oxm.UnmarshallingFailureException;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

public class WebUtil {
	
	/** The logger. */
	private static XLogger LOGGER = XLoggerFactory.getXLogger(WebUtil.class.getName());
	/**
	 * 
	 * @param reader
	 * @return
	 * @throws IOException
	 */
	public static String readRequestPayLoad(java.io.Reader reader)
			throws IOException {

		StringBuilder bul = new StringBuilder();
		BufferedReader buf = new BufferedReader(reader);
		String line = buf.readLine();
		while (line != null) {
			line = line.trim();
			bul.append(line);
			line = buf.readLine();
		}

		if (bul.length() > 0) {
			LOGGER.debug("Received Post Body");
		} else {
			LOGGER.debug("Post Body is Empty");
		}

		return bul.toString();
	}
	
	public static Object unmarshall(Jaxb2Marshaller marshaller,
			String requrstStr) {
		
		Object reqObj = null;
			try{
			 reqObj = marshaller.unmarshal(new StreamSource(
					new StringReader(requrstStr)));
			}catch(UnmarshallingFailureException e){
				LOGGER.error("JAXB Exception for UnmarshallingFailureException error: {}",e.getMessage());
			}
			catch(IllegalArgumentException e){
				LOGGER.error("JAXB Exception for IllegalArgumentException error: {}", e.getMessage());
			}
			catch(Exception e){
					LOGGER.error("JAXB Exception for error: {}",e.getMessage());
			}

		return reqObj;
	}
}
