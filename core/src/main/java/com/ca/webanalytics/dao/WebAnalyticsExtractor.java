package com.ca.webanalytics.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.ca.webanalytics.entity.PageViews;
import com.ca.webanalytics.entity.WebAnalytics;

/**
 * Resultset extractor
 * 
 * @author rkodali
 * 
 */
public class WebAnalyticsExtractor implements
		ResultSetExtractor<List<WebAnalytics>> {

	private static XLogger LOGGER = XLoggerFactory
			.getXLogger(WebAnalyticsExtractor.class.getName());

	@Override
	public List<WebAnalytics> extractData(ResultSet resultSet)
			throws SQLException, DataAccessException {

		List<WebAnalytics> siteAnalyticsList = new ArrayList<WebAnalytics>();

		Map<Integer, WebAnalytics> analyticsMap = new HashMap<Integer, WebAnalytics>();

		WebAnalytics analytics = null;
		try {

			PageViewsRowMapper pageViewsRowMapper = new PageViewsRowMapper();

			while (resultSet.next()) {

				Integer website_id = resultSet.getInt("id");

				PageViews pageViews = pageViewsRowMapper.mapRow(resultSet,resultSet.getRow());

				if (!analyticsMap.containsKey(website_id)) {
					analytics = new WebAnalytics();
					analytics.setWebsite_id(website_id);
					
					analytics.setDomainName(resultSet.getString("domain_name"));

					analytics.setCountry(resultSet.getString("country"));

					analytics.setState(resultSet.getString("state"));

					analytics.setCity(resultSet.getString("city"));
					
					analytics.setIpAddress(resultSet.getString("ip_address"));

					analytics.addPageViews(pageViews);
					
					analyticsMap.put(website_id, analytics);
				} else {
					analytics = analyticsMap.get(website_id);
					analytics.addPageViews(pageViews);
				}
			}

		} catch (SQLException ex) {
			LOGGER.error(
					"Exception occurred while processing the resultSet: {}",
					ex.getMessage());
		}

		siteAnalyticsList.addAll(analyticsMap.values());
		
		LOGGER.exit(siteAnalyticsList);

		return siteAnalyticsList;
	}
}
