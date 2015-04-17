package com.ca.webanalytics.repository;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ca.webanalytics.dao.WebAnalyticsDao;
import com.ca.webanalytics.entity.WebAnalytics;
import com.ca.webanalytics.entity.WebAnalyticsList;
import com.ca.webanalytics.exception.ServiceException;

/**
 * Repository Implementation
 * 
 * @author rkodali
 *
 */
@Repository("webAnalyticsRepoitory")
public class WebAnalyticsRepositoryImpl implements WebAnalyticsRepository{
       
	@Autowired
	private WebAnalyticsDao webAnalyticsDao;

	/*
	 * (non-Javadoc)
	 * @see com.ca.webanalytics.repository.WebAnalyticsRepository#getSitePageViews(java.lang.Integer)
	 */
	@Override
	public WebAnalytics getSitePageViews(Integer website_id) {
		WebAnalytics webAnalytics = null;
		  try{
			  webAnalytics =  webAnalyticsDao.getSitePageViews(website_id);
		  }catch(DataAccessException e){
			  throw new ServiceException(e);
		  }
		return webAnalytics;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ca.webanalytics.repository.WebAnalyticsRepository#getSitePageViewsForGivenDateRange(java.lang.Integer, java.util.Date, java.util.Date)
	 */
	@Override
	public WebAnalytics getSitePageViewsForGivenDateRange(Integer website_id,
			Date fromDate, Date toDate) {
		WebAnalytics webAnalytics = null;
		  try{
			  webAnalytics =  webAnalyticsDao.getSitePageViewsForGivenDateRange(website_id, fromDate, toDate);
		  }catch(DataAccessException e){
			  throw new ServiceException(e);
		  }
		return webAnalytics;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ca.webanalytics.repository.WebAnalyticsRepository#getSitePageViewsForCountryCode(java.lang.Integer, java.lang.String)
	 */
	@Override
	public WebAnalytics getSitePageViewsForCountryCode(Integer website_id,
			String countryCode) {
		WebAnalytics webAnalytics = null;
		  try{
			  webAnalytics =  webAnalyticsDao.getSitePageViewsForCountryCode(website_id, countryCode);
		  }catch(DataAccessException e){
			  throw new ServiceException(e);
		  }
		return webAnalytics;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ca.webanalytics.repository.WebAnalyticsRepository#getSitePageViewsForCountryCodeInDateRange(java.lang.Integer, java.lang.String, java.util.Date, java.util.Date)
	 */
	@Override
	public WebAnalytics getSitePageViewsForCountryCodeInDateRange(
			Integer website_id, String countryCode, Date fromDate, Date toDate) {
		WebAnalytics webAnalytics = null;
		  try{
			  webAnalytics =  webAnalyticsDao.getSitePageViewsForCountryCodeInDateRange(website_id, countryCode, fromDate, toDate);
		  }catch(DataAccessException e){
			  throw new ServiceException(e);
		  }
		return webAnalytics;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ca.webanalytics.repository.WebAnalyticsRepository#getPopularPageViews()
	 */
	@Override
	public WebAnalyticsList getPopularPageViews() {
		WebAnalyticsList webAnalyticsCollection = null;
		  try{
			  webAnalyticsCollection =  webAnalyticsDao.getPopularPageViews();
		  }catch(DataAccessException e){
			  throw new ServiceException(e);
		  }
		return webAnalyticsCollection;
	}

	@Override
	public WebAnalyticsList getPageViewsForPageViewRange(
			Integer pageViewCounterLowerBound, Integer pageViewCounterUpperBound) {
		WebAnalyticsList webAnalyticsCollection = null;
		  try{
			  webAnalyticsCollection =  webAnalyticsDao.getPageViewsForPageViewRange(pageViewCounterLowerBound, pageViewCounterUpperBound);
		  }catch(DataAccessException e){
			  throw new ServiceException(e);
		  }
		return webAnalyticsCollection;
	}

	@Override
	public WebAnalyticsList getPageViewsForGivenPageViewCounter(
			Integer pageViewCounter) {
		WebAnalyticsList webAnalyticsCollection = null;
		  try{
			  webAnalyticsCollection =  webAnalyticsDao.getPageViewsForGivenPageViewCounter(pageViewCounter);
		  }catch(DataAccessException e){
			  throw new ServiceException(e);
		  }
		return webAnalyticsCollection;
	}

	@Override
	public WebAnalyticsList getPopularPageViewsInCountry(String country) {
		WebAnalyticsList webAnalyticsCollection = null;
		  try{
			  webAnalyticsCollection =  webAnalyticsDao.getPopularPageViewsInCountry(country);
		  }catch(DataAccessException e){
			  throw new ServiceException(e);
		  }
		return webAnalyticsCollection;
	}

	@Override
	public WebAnalytics createPageViewsInDB(WebAnalytics webAnalytics) {
		  try{
			  webAnalytics =  webAnalyticsDao.createPageViewsInDB(webAnalytics);
		  }catch(DataAccessException e){
			  throw new ServiceException(e);
		  }
		return webAnalytics;
	}

}
