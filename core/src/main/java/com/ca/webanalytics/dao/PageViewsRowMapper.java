package com.ca.webanalytics.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ca.webanalytics.entity.PageViews;
import com.ca.webanalytics.util.DateUtil;

public class PageViewsRowMapper implements	RowMapper<PageViews> {

	private static XLogger LOGGER = XLoggerFactory
			.getXLogger(PageViewsRowMapper.class.getName());

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public PageViews mapRow(ResultSet rs, int rowNum) throws SQLException {
		LOGGER.entry();
		PageViews pageViews = new PageViews();
		
		pageViews.setPagePath(rs.getString("pagePath"));
		
		pageViews.setDaysSinceLastVisit(rs.getInt("daysSinceLastVisit"));
		
		pageViews.setLastVisitedDate(DateUtil.dateToCalendar(rs.getDate("lastVisitedDate")));
		
		pageViews.setPageViewCounter(rs.getInt("pageViewCounter"));
		
		pageViews.setVisitors(rs.getInt("visitors"));
		
		pageViews.setUniqueVisitors(rs.getInt("uniqueVisitors"));
		
		pageViews.setTimeOnPge(DateUtil.dateToCalendar(rs.getTime("timeOnPage")));
		
		pageViews.setAvgTimeOnPage(DateUtil.dateToCalendar(rs.getTime("averageTimeOnPage")));
		
		LOGGER.exit(pageViews);
		return pageViews;
	}
}
