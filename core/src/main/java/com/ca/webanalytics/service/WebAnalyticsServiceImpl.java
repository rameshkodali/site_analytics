package com.ca.webanalytics.service;

import java.util.Date;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.webanalytics.entity.WebAnalytics;
import com.ca.webanalytics.entity.WebAnalyticsList;
import com.ca.webanalytics.exception.ServiceException;
import com.ca.webanalytics.repository.WebAnalyticsRepository;

/**
 * Service Implementation
 * 
 * @author rkodali
 *
 */
@Service("webAnalyticsService")
public class WebAnalyticsServiceImpl implements WebAnalyticsService{
	
	@Autowired
	private WebAnalyticsRepository webAnalyticsRepository;
	
	private static XLogger LOGGER = XLoggerFactory.getXLogger(WebAnalyticsServiceImpl.class.getName());

	/*
	 * (non-Javadoc)
	 * @see com.ca.webanalytics.service.WebAnalyticsService#getSitePageViews(java.lang.Integer)
	 */
	@Override
	public WebAnalytics getSitePageViews(Integer website_id) {
		WebAnalytics webAnalytics = null;
		  try{
			  webAnalytics =  webAnalyticsRepository.getSitePageViews(website_id);
		  }catch(ServiceException e){
			  LOGGER.error(" ServiceException is : {}",e.getMessage());
		  }
		return webAnalytics;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ca.webanalytics.service.WebAnalyticsService#getSitePageViewsForGivenDateRange(java.lang.Integer, java.util.Date, java.util.Date)
	 */
	@Override
	public WebAnalytics getSitePageViewsForGivenDateRange(Integer website_id,Date fromDate, Date toDate) {
		WebAnalytics webAnalytics = null;
		  try{
			  webAnalytics = webAnalyticsRepository.getSitePageViewsForGivenDateRange(website_id, fromDate, toDate);
		  }catch(ServiceException e){
			  LOGGER.error(" ServiceException is : {}",e.getMessage());
		  }
		return webAnalytics;
	}

    /*
     * (non-Javadoc)
     * @see com.ca.webanalytics.service.WebAnalyticsService#getSitePageViewsForCountryCode(java.lang.Integer, java.lang.String)
     */
	@Override
	public WebAnalytics getSitePageViewsForCountryCode(Integer website_id,
			String countryCode) {
		WebAnalytics webAnalytics = null;
		  try{
			  webAnalytics = webAnalyticsRepository.getSitePageViewsForCountryCode(website_id, countryCode);
		  }catch(ServiceException e){
			  LOGGER.error(" ServiceException is : {}",e.getMessage());
		  }
		return webAnalytics;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ca.webanalytics.service.WebAnalyticsService#getSitePageViewsForCountryCodeInDateRange(java.lang.Integer, java.lang.String, java.util.Date, java.util.Date)
	 */
	@Override
	public WebAnalytics getSitePageViewsForCountryCodeInDateRange(
			Integer website_id, String countryCode, Date fromDate, Date toDate) {
		WebAnalytics webAnalytics = null;
		  try{
			  webAnalytics = webAnalyticsRepository.getSitePageViewsForCountryCodeInDateRange(website_id, countryCode, fromDate, toDate);
		  }catch(ServiceException e){
			  LOGGER.error(" ServiceException is : {}",e.getMessage());
		  }
		return webAnalytics;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ca.webanalytics.service.WebAnalyticsService#getPopularPageViews()
	 */
	@Override
	public WebAnalyticsList getPopularPageViews() {
		WebAnalyticsList webAnalyticsCollection = null;
		  try{
			  webAnalyticsCollection = webAnalyticsRepository.getPopularPageViews();
		  }catch(ServiceException e){
			  LOGGER.error(" ServiceException is : {}",e.getMessage());
		  }
		return webAnalyticsCollection;
	}

	@Override
	public WebAnalyticsList getPageViewsForPageViewRange(
			Integer pageViewCounterLowerBound, Integer pageViewCounterUpperBound) {
		WebAnalyticsList webAnalyticsCollection = null;
		  try{
			  webAnalyticsCollection = webAnalyticsRepository.getPageViewsForPageViewRange(pageViewCounterLowerBound, pageViewCounterUpperBound);
		  }catch(ServiceException e){
			  LOGGER.error(" ServiceException is : {}",e.getMessage());
		  }
		return webAnalyticsCollection;
	}

	@Override
	public WebAnalyticsList getPageViewsForGivenPageViewCounter(
			Integer pageViewCounter) {
		WebAnalyticsList webAnalyticsCollection = null;
		  try{
			  webAnalyticsCollection = webAnalyticsRepository.getPageViewsForGivenPageViewCounter(pageViewCounter);
		  }catch(ServiceException e){
			  LOGGER.error(" ServiceException is : {}",e.getMessage());
		  }
		return webAnalyticsCollection;
	}

	@Override
	public WebAnalyticsList getPopularPageViewsInCountry(String country) {
		WebAnalyticsList webAnalyticsCollection = null;
		  try{
			  webAnalyticsCollection = webAnalyticsRepository.getPopularPageViewsInCountry(country);
		  }catch(ServiceException e){
			  LOGGER.error(" ServiceException is : {}",e.getMessage());
		  }
		return webAnalyticsCollection;
	}

	@Override
	public WebAnalytics createPageViewsInDB(WebAnalytics webAnalytics) {
		  try{
			  webAnalytics = webAnalyticsRepository.createPageViewsInDB(webAnalytics);
		  }catch(ServiceException e){
			  LOGGER.error(" ServiceException is : {}",e.getMessage());
		  }
		return webAnalytics;
	}

}
