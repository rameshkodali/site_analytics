package com.ca.webanalytics.service;

import java.util.Date;

import com.ca.webanalytics.entity.WebAnalytics;
import com.ca.webanalytics.entity.WebAnalyticsList;

/**
 * Interface for Service methods
 * 
 * @author rkodali
 *
 */
public interface WebAnalyticsService {

	/**
	 * Get the Page Views for given site from the Repository
	 * @param website_id
	 * @return SiteAnaltics
	 */
	public abstract WebAnalytics getSitePageViews(Integer website_id);
	
	/**
	 * Get the Page views for the given date range for given site id from the Repository
	 * @param website_id
	 * @param fromDate
	 * @param toDate
	 * @return SiteAnalytics
	 */
	public abstract WebAnalytics getSitePageViewsForGivenDateRange(Integer website_id, Date fromDate,Date toDate);
	
	/**
	 * Get the Page Views for Country Code from the DAO
	 * @param website_id
	 * @param countryCode
	 * @return SiteAnalytics
	 */
	public abstract WebAnalytics getSitePageViewsForCountryCode(Integer website_id, String countryCode);
	
	/**
	 * Get the Page Views for Given Date range, country code and site id from the Repository
	 * @param website_id
	 * @param countryCode
	 * @param fromDate
	 * @param toDate
	 * @return SiteAnalytics 
	 */
	public abstract WebAnalytics getSitePageViewsForCountryCodeInDateRange(Integer website_id, String countryCode,Date fromDate,Date toDate);
	
	/**
	 * Get the most popular page views across all the sites from the Repository
	 * 
	 * @return webAnalyticsCollection
	 */
	public abstract WebAnalyticsList getPopularPageViews();
	
	/**
	 * Get the  page views across all the sites for the selected page view counter lower and upper bounds
	 * @return webAnalyticsList
	 */
	public abstract WebAnalyticsList getPageViewsForPageViewRange(final Integer pageViewCounterLowerBound, final Integer pageViewCounterUpperBound);
	
	/**
	 * Get the  page views across all the sites for the given page view counter
	 * @return webAnalyticsList
	 */
	public abstract WebAnalyticsList  getPageViewsForGivenPageViewCounter(final Integer pageViewCounter);
	
	/**
	 * Get the  page views across all the sites in a given country
	 * @return webAnalyticsList
	 */
	public abstract WebAnalyticsList  getPopularPageViewsInCountry(final String country);
	
	/**
	 * Persists the Page Views to database
	 * 
	 * @param webAnalytics
	 * @return webAnalytics with auto generated Id
	 */
	public abstract WebAnalytics createPageViewsInDB(WebAnalytics webAnalytics);
	  
}
