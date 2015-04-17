package com.ca.webanalytic.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ca.webanalytics.entity.WebAnalytics;
import com.ca.webanalytics.entity.WebAnalyticsList;
import com.ca.webanalytics.rest.util.WebUtil;
import com.ca.webanalytics.service.WebAnalyticsService;
import com.ca.webanalytics.util.DateUtil;

@Controller("webAnalyticsController")
public class WebAnalyticsController {

	private static XLogger LOGGER = XLoggerFactory
			.getXLogger(WebAnalyticsController.class.getName());

	@Autowired
	@Qualifier("marshaller")
	private Jaxb2Marshaller marshaller;

	@Autowired
	private WebAnalyticsService webAnalyticsService;

	enum OperationNames {
		pageViewsForSite, pageViewsForCountry, pageViewsForDateRange, pageViewsForCountryInDateRange, poularPageViews,pageViewsForPageViewCounterRange, pageViewsForGivenPageViewCounter,popularPageViewsInCountry
	}


	/**
	 * 
	 * Gives page views analytics for given site
	 * 
	 * @param website_id
	 * @param countryCode
	 * @param fromDate
	 * @param toDate
	 * @param operationName
	 * @return webanalytics
	 */
	@RequestMapping(value = "/webanalyticslist/{website_id}/webanalytics", method = RequestMethod.GET)
	public ResponseEntity<Object> getWebAnalytics(
			@PathVariable("website_id") String website_id,
			@RequestParam(value = "countryCode", required = false) String countryCode,
			@RequestParam(value = "fromDate", required = false) String fromDate,
			@RequestParam(value = "toDate", required = false) String toDate,
			@RequestParam(value = "operation", required = true) String operationName,
			HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) {

		LOGGER.entry(website_id,countryCode,fromDate,toDate,operationName);

		OperationNames operation;
		try {
			operation = OperationNames.valueOf(operationName);
		} catch (IllegalArgumentException e) {
			LOGGER.error("Incorrect operation {}", e.getMessage());
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
		
		LOGGER.debug("operation name is : {}",operation);
		
		WebAnalytics webanalytics = null;
		switch (operation) {
		case pageViewsForSite:
			webanalytics = webAnalyticsService.getSitePageViews(Integer.parseInt(website_id));
			break;
		case pageViewsForCountry:
			webanalytics = webAnalyticsService.getSitePageViewsForCountryCode(
					Integer.parseInt(website_id), countryCode);
			break;
		case pageViewsForDateRange:
			webanalytics = webAnalyticsService.getSitePageViewsForGivenDateRange(Integer.parseInt(website_id),DateUtil.parseDate(fromDate),DateUtil.parseDate(toDate));
			break;
		case pageViewsForCountryInDateRange:
			webanalytics = webAnalyticsService.getSitePageViewsForCountryCodeInDateRange(Integer.parseInt(website_id), countryCode,DateUtil.parseDate(fromDate),DateUtil.parseDate(toDate));
			break;
			
		default:
			break;
		}
		return new ResponseEntity<Object>(webanalytics,HttpStatus.OK);
	}

	/**
	 * 
	 * Get the popular pages views across all the web sites based on different input
	 * 
	 * @param operationName
	 * @return webAnalyticsCollection
	 */
	@RequestMapping(value = "/webanalyticslist", method = RequestMethod.GET)
	public ResponseEntity<Object> getWebAnalyticsList(
			@RequestParam(value = "countryCode", required = false) String countryCode,
			@RequestParam(value = "pageViewCounterLowerBound", required = false) String pageViewCounterLowerBound,
			@RequestParam(value = "pageViewCounterUpperBound", required = false) String pageViewCounterUpperBound,
			@RequestParam(value = "pageViewCounter", required = false) String pageViewCounter,
			@RequestParam(value = "operation", required = true) String operationName,
			HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) {

		LOGGER.entry(countryCode,pageViewCounterLowerBound,pageViewCounterUpperBound,pageViewCounter, operationName);

		OperationNames operation;
		try {
			operation = OperationNames.valueOf(operationName);
		} catch (IllegalArgumentException e) {
			LOGGER.error("Incorrect operation {}", e.getMessage());
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
		
		LOGGER.debug("operation name is : {}",operation);
		
		
		WebAnalyticsList webAnalyticsCollection = null;
		switch (operation) {
		case poularPageViews:
			webAnalyticsCollection = webAnalyticsService.getPopularPageViews();
			break;
		case pageViewsForPageViewCounterRange:
			webAnalyticsCollection = webAnalyticsService.getPageViewsForPageViewRange(Integer.parseInt(pageViewCounterLowerBound), Integer.parseInt(pageViewCounterUpperBound));
			break;
		case pageViewsForGivenPageViewCounter:
			webAnalyticsCollection = webAnalyticsService.getPageViewsForGivenPageViewCounter(Integer.parseInt(pageViewCounter));
			break;
		case popularPageViewsInCountry:
			webAnalyticsCollection = webAnalyticsService.getPopularPageViewsInCountry(countryCode);
			break;
		default:
			break;
		}
		return new ResponseEntity<Object>(webAnalyticsCollection,HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/webanalytics", method = RequestMethod.POST)
	public ResponseEntity<Object> createWebAnalytics(
			HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) {

		String requestStr = null; 
		try { 
			requestStr  =  WebUtil.readRequestPayLoad(servletRequest.getReader()); 
		} catch(Exception e) 
		{ 
			LOGGER.error("Invalid payload {}", e.getMessage());
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		} 
		WebAnalytics webAnalytics = (WebAnalytics) WebUtil .unmarshall(marshaller, requestStr);
		webAnalyticsService.createPageViewsInDB(webAnalytics);
		return new ResponseEntity<Object>(webAnalytics,HttpStatus.OK);
	}
	
	//TODO Get the report based on the visitors also.
	//TODO Get report based on Time Spent on site to understand more used site
	//TODO Get report based on browser, region, city,...
	
}
