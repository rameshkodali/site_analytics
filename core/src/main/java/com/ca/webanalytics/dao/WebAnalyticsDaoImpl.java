package com.ca.webanalytics.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.ca.webanalytics.entity.PageViews;
import com.ca.webanalytics.entity.WebAnalytics;
import com.ca.webanalytics.entity.WebAnalyticsList;
import com.ca.webanalytics.util.DBQueryUtil;

/**
 * DAO implementation
 * 
 * @author rkodali
 *
 */
@Component("webAnalyticsDao")
public class WebAnalyticsDaoImpl implements WebAnalyticsDao{
	
	private static XLogger LOGGER = XLoggerFactory.getXLogger(WebAnalyticsDaoImpl.class.getName());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/*
	 * (non-Javadoc)
	 * @see com.ca.webanalytics.dao.WebAnalyticsDao#getSitePageViews(java.lang.Integer)
	 */
	@Override
	public WebAnalytics getSitePageViews(Integer website_id){
		LOGGER.debug("Retrieving the Page Views for the site id {}",website_id);
		List<WebAnalytics> siteAnalyticsList =  jdbcTemplate.query(DBQueryUtil.GET_PAGEVIEWS_FOR_SITE.toString(), new Object[]{website_id}, new WebAnalyticsExtractor());
		return siteAnalyticsList.get(0);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ca.webanalytics.dao.WebAnalyticsDao#getSitePageViewsForGivenDateRange(java.lang.Integer, java.util.Date, java.util.Date)
	 */
	@Override
	public WebAnalytics getSitePageViewsForGivenDateRange(final Integer website_id, final Date fromDate,final Date toDate){
		LOGGER.debug("Retrieving the Page Views for the site id {}, for given from date {} and to date {}",new Object[]{website_id,fromDate,toDate});
		List<WebAnalytics> siteAnalyticsList  =jdbcTemplate.query(DBQueryUtil.GET_PAGEVIEWS_FOR_SITE_FOR_DATE_RANGE.toString(), new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps)
					throws SQLException {
				 ps.setInt(1, website_id);
				 ps.setTimestamp(2, new java.sql.Timestamp(fromDate.getTime()));
				 ps.setTimestamp(3, new java.sql.Timestamp(toDate.getTime()));
			}
        }, new WebAnalyticsExtractor());
		
		return siteAnalyticsList.get(0);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ca.webanalytics.dao.WebAnalyticsDao#getSitePageViewsForCountryCode(java.lang.Integer, java.lang.String)
	 */
	@Override
	public WebAnalytics getSitePageViewsForCountryCode(final Integer website_id, final String countryCode){
		LOGGER.debug("Retrieving the Page Views for the site id {}, for country {}",website_id,countryCode);
		List<WebAnalytics> siteAnalyticsList  =jdbcTemplate.query(DBQueryUtil.GET_PAGEVIEWS_FOR_SITE_FOR_COUNTRY_CODE.toString(), new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps)
					throws SQLException {
				 ps.setInt(1, website_id);
				 ps.setString(2, countryCode);
			}
        }, new WebAnalyticsExtractor());
		
		return siteAnalyticsList.get(0);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ca.webanalytics.dao.WebAnalyticsDao#getSitePageViewsForCountryCodeInDateRange(java.lang.Integer, java.lang.String, java.util.Date, java.util.Date)
	 */
	@Override
	public WebAnalytics getSitePageViewsForCountryCodeInDateRange(final Integer website_id, final String countryCode,final Date fromDate,final Date toDate){
		LOGGER.debug("Retrieving the Page Views for the site id {}, for country{} ,for given from date {} and to date {}",new Object[]{website_id,countryCode,fromDate,toDate});
		List<WebAnalytics> siteAnalyticsList  =jdbcTemplate.query(DBQueryUtil.GET_PAGEVIEWS_FOR_SITE_FOR_COUNTRY_CODE_FOR_DATE_RANGE.toString(), new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps)
					throws SQLException {
				 ps.setInt(1, website_id);
				 ps.setString(2, countryCode);
				 ps.setTimestamp(3, new java.sql.Timestamp(fromDate.getTime()));
				 ps.setTimestamp(4, new java.sql.Timestamp(toDate.getTime()));
			}
        }, new WebAnalyticsExtractor());
		
		return siteAnalyticsList.get(0);
	}
	
    /*
     * (non-Javadoc)
     * @see com.ca.webanalytics.dao.WebAnalyticsDao#getPopularPageViews()
     */
	@Override
	public WebAnalyticsList getPopularPageViews(){
		LOGGER.debug("Retrieving the popular page views across sites");
		WebAnalyticsList webAnalyticsCollection = new WebAnalyticsList();
		List<WebAnalytics> siteAnalyticsList  =jdbcTemplate.query(DBQueryUtil.GET_POPULAR_PAGEVIEWS_ACROSS_SITES.toString(), new WebAnalyticsExtractor());
		webAnalyticsCollection.addWebAnalyticsList(siteAnalyticsList);
		return webAnalyticsCollection;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ca.webanalytics.dao.WebAnalyticsDao#getPageViewsForPageViewRange(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public WebAnalyticsList getPageViewsForPageViewRange(final Integer pageViewCounterLowerBound, final Integer pageViewCounterUpperBound) {
		LOGGER.debug("Retrieving the  page views across sites for the given Page View counter boundary limits from {} and to {}",pageViewCounterLowerBound,pageViewCounterUpperBound);
		WebAnalyticsList webAnalyticsCollection = new WebAnalyticsList();
		List<WebAnalytics> siteAnalyticsList  =jdbcTemplate.query(DBQueryUtil.GET_PAGEVIEWS_ACROSS_SITES_FOR_PAGE_VIEW_COUNTER_RANGE.toString(), new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps)
					throws SQLException {
				 ps.setInt(1, pageViewCounterLowerBound);
				 ps.setInt(2, pageViewCounterUpperBound);
			}
        }, new WebAnalyticsExtractor());
		webAnalyticsCollection.addWebAnalyticsList(siteAnalyticsList);
		return webAnalyticsCollection;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ca.webanalytics.dao.WebAnalyticsDao#getPageViewsForGivenPageViewCounter(java.lang.Integer)
	 */
	@Override
	public WebAnalyticsList getPageViewsForGivenPageViewCounter(final Integer pageViewCounter) {
		LOGGER.debug("Retrieving the  page views across sites for the given page view counter {}",pageViewCounter);
		WebAnalyticsList webAnalyticsCollection = new WebAnalyticsList();
		List<WebAnalytics> siteAnalyticsList  =jdbcTemplate.query(DBQueryUtil.GET_PAGEVIEWS_FOR_SITE_FOR_GIVEN_PAGE_VIEW_COUNTER.toString(), new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps)
					throws SQLException {
				 ps.setInt(1, pageViewCounter);
			}
        }, new WebAnalyticsExtractor());
		webAnalyticsCollection.addWebAnalyticsList(siteAnalyticsList);
		return webAnalyticsCollection;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ca.webanalytics.dao.WebAnalyticsDao#getPopularPageViewsInCountry(java.lang.String)
	 */
	@Override
	public WebAnalyticsList getPopularPageViewsInCountry(final String country) {
		LOGGER.debug("Retrieving the  page views across sites for the given country {}",country);
		WebAnalyticsList webAnalyticsCollection = new WebAnalyticsList();
		List<WebAnalytics> siteAnalyticsList  =jdbcTemplate.query(DBQueryUtil.GET_POPULAR_SITES_IN_A_COUNTRY.toString(), new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps)
					throws SQLException {
				 ps.setString(1, country);
			}
        }, new WebAnalyticsExtractor());
		webAnalyticsCollection.addWebAnalyticsList(siteAnalyticsList);
		return webAnalyticsCollection;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ca.webanalytics.dao.WebAnalyticsDao#createPageViewsInDB(com.ca.webanalytics.entity.WebAnalytics)
	 */
	@Override
	public WebAnalytics createPageViewsInDB(final WebAnalytics webAnalytics) {
		//Key holder to capture the auto generated primary key in database
		KeyHolder holder = new GeneratedKeyHolder();

		Integer website_id = webAnalytics.getWebsite_id();
		//At this point, website id in the request is null. That means, this web site is new for tracking and adding website to databae
		if(webAnalytics.getWebsite_id()  == null){
			jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(
						Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(
							DBQueryUtil.INSERT_WEB_ANALYTICS_ROW_QUERY.toString(), new String[] {"ID"});
					ps.setString(1, webAnalytics.getDomainName());
					ps.setString(2, webAnalytics.getCountry());
					ps.setString(3, webAnalytics.getState());
					ps.setString(4, webAnalytics.getCity());
					ps.setString(5, webAnalytics.getBrowser());
					ps.setString(6, webAnalytics.getIpAddress());
					return ps;
				}
			}, holder);
			
			website_id = holder.getKey().intValue();
		}
		
		final Integer wbsite_id = website_id;
		
		for(final PageViews pageView : webAnalytics.getPageViews()){
			jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(
						Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(
							DBQueryUtil.INSERT_PAGE_VIEWS_ROW_QUERY.toString());
					ps.setString(1, pageView.getPagePath());
					ps.setInt(2, wbsite_id);
					ps.setInt(3, pageView.getDaysSinceLastVisit());
					ps.setTimestamp(4, new java.sql.Timestamp(pageView.getLastVisitedDate().getTime().getTime()));
					ps.setInt(5, pageView.getPageViewCounter());
					ps.setInt(6, pageView.getVisitors());
					ps.setInt(7, pageView.getUniqueVisitors());
					ps.setTimestamp(8, new java.sql.Timestamp(pageView.getTimeOnPge().getTime().getTime()));
					ps.setTimestamp(9, new java.sql.Timestamp(pageView.getAvgTimeOnPage().getTime().getTime()));
					return ps;
				}
			});
		}
		
		//TODO check page is already viewed for the given web site, updates the page view metrics like pageViewCounter, visitorsCount, visited Time,......
		
		webAnalytics.setWebsite_id(wbsite_id);
		return webAnalytics;
	}


	/**
	 * Launch the HSQLDB Database manager in GUI mode which helps to perform the CRUD operations on in-memory database.
	 * By default turned off. To enable this setting, use system property in maven commands "-DlaunchHsqlDBManager=true".
	 * for e.g; mvn jetty:run -DlaunchHsqlDBManager=true
	 */
	@PostConstruct
    public void startUpHsqlDBDataBaseManager()
    {
	   String launchHSQLDBDataBaseManager = System.getProperty("launchHsqlDBManager");	
	   if(StringUtils.hasText(launchHSQLDBDataBaseManager) && "true".equalsIgnoreCase(launchHSQLDBDataBaseManager)){
	       org.hsqldb.util.DatabaseManagerSwing.main(new String[] { "--url","jdbc:hsqldb:mem:testdatabase", "--noexit" });   
	   }
    }

}
