package com.ca.webanalytics.util;

public class DBQueryUtil {
	
	public static final StringBuilder GET_PAGEVIEWS_FOR_SITE = new StringBuilder(
			"select id, domain_name, country, state, city, ip_address, pagePath, daysSinceLastVisit, lastVisitedDate, pageViewCounter, visitors, uniqueVisitors, timeOnPage, averageTimeOnPage")
			.append(" from testdatabase.WebAnalytics wa, testdatabase.PageViews pv where wa.id =  pv.website_id and wa.id = ?");

	public static final StringBuilder GET_PAGEVIEWS_FOR_SITE_FOR_DATE_RANGE = new StringBuilder(
			"select id, domain_name, country, state, city, ip_address, pagePath, daysSinceLastVisit, lastVisitedDate, pageViewCounter,visitors, uniqueVisitors, timeOnPage, averageTimeOnPage")
			.append(" from testdatabase.WebAnalytics wa, testdatabase.PageViews pv where wa.id =  pv.website_id and wa.id = ? and pv.lastVisitedDate between ? and ?");

	public static final StringBuilder GET_PAGEVIEWS_FOR_SITE_FOR_COUNTRY_CODE = new StringBuilder(
			"select id, domain_name, country, state, city, ip_address, pagePath, daysSinceLastVisit, lastVisitedDate, pageViewCounter,visitors, uniqueVisitors, timeOnPage, averageTimeOnPage")
			.append(" from testdatabase.WebAnalytics wa, testdatabase.PageViews pv where wa.id =  pv.website_id and wa.id = ? and wa.country = ?");

	public static final StringBuilder GET_PAGEVIEWS_FOR_SITE_FOR_COUNTRY_CODE_FOR_DATE_RANGE = new StringBuilder(
			"select id, domain_name ,country, state, city, ip_address, pagePath, daysSinceLastVisit, lastVisitedDate, pageViewCounter,visitors, uniqueVisitors, timeOnPage, averageTimeOnPage")
			.append(" from testdatabase.WebAnalytics wa, testdatabase.PageViews pv where wa.id =  pv.website_id and wa.id = ? and wa.country = ? and pv.lastVisitedDate between ? and ?");

	public static final StringBuilder GET_POPULAR_PAGEVIEWS_ACROSS_SITES = new StringBuilder(
			"select wa.id, wa.domain_name ,wa.country, wa.state, wa.city, wa.ip_address, pv.pagePath, pv.daysSinceLastVisit, pv.lastVisitedDate, pv.pageViewCounter, pv.visitors, pv.uniqueVisitors, pv.timeOnPage, pv.averageTimeOnPage")
			.append(" from testdatabase.WebAnalytics wa, testdatabase.PageViews pv where wa.id =  pv.website_id and pv.pageViewCounter in (select max(pageViewCounter) ")
			.append(" from testdatabase.PageViews group by website_id)");
	
	public static final StringBuilder GET_PAGEVIEWS_ACROSS_SITES_FOR_PAGE_VIEW_COUNTER_RANGE = new StringBuilder(
			"select wa.id, wa.domain_name, wa.country, wa.state, wa.city, wa.ip_address, pv.pagePath, pv.daysSinceLastVisit, pv.lastVisitedDate, pv.pageViewCounter, pv.visitors, pv.uniqueVisitors, pv.timeOnPage, pv.averageTimeOnPage")
			.append(" from testdatabase.WebAnalytics wa, testdatabase.PageViews pv where wa.id =  pv.website_id and pv.pageViewCounter between ? and ?");
	
	public static final StringBuilder GET_PAGEVIEWS_FOR_SITE_FOR_GIVEN_PAGE_VIEW_COUNTER = new StringBuilder(
			"select wa.id, wa.domain_name, wa.country, wa.state, wa.city, wa.ip_address, pv.pagePath, pv.daysSinceLastVisit, pv.lastVisitedDate, pv.pageViewCounter, pv.visitors, pv.uniqueVisitors, pv.timeOnPage, pv.averageTimeOnPage")
			.append(" from testdatabase.WebAnalytics wa, testdatabase.PageViews pv where wa.id =  pv.website_id and pv.pageViewCounter = ?");
	
	public static final StringBuilder GET_POPULAR_SITES_IN_A_COUNTRY = new StringBuilder(
			"select wa.id, wa.domain_name ,wa.country, wa.state, wa.city, wa.ip_address ,pv.pagePath, pv.daysSinceLastVisit, pv.lastVisitedDate, pv.pageViewCounter, pv.visitors, pv.uniqueVisitors, pv.timeOnPage, pv.averageTimeOnPage")
			.append(" from testdatabase.WebAnalytics wa, testdatabase.PageViews pv where wa.id =  pv.website_id and wa.country = ? and pv.pageViewCounter in (select max(pageViewCounter) ")
			.append(" from testdatabase.PageViews group by website_id)");
	
	public static final StringBuilder GET_PAGEVIEWS_FOR_UNIQUE_VISITORS = new StringBuilder(
			"select id, domain_name, country, state, city, ip_address, pagePath, daysSinceLastVisit, lastVisitedDate, pageViewCounter, visitors, uniqueVisitors, timeOnPage, averageTimeOnPage")
			.append(" from testdatabase.WebAnalytics wa, testdatabase.PageViews pv where wa.id =  pv.website_id and pv.uniqueVisitors = ?");
	
	public static final StringBuilder GET_PAGEVIEWS_FOR_DIFFERENT_VISITORS = new StringBuilder(
			"select id, domain_name, country, state, city, ip_address, pagePath, daysSinceLastVisit, lastVisitedDate, pageViewCounter, visitors, uniqueVisitors, timeOnPage, averageTimeOnPage")
			.append(" from testdatabase.WebAnalytics wa, testdatabase.PageViews pv where wa.id =  pv.website_id and pv.visitors = ?");
	
	public static final StringBuilder INSERT_WEB_ANALYTICS_ROW_QUERY = new StringBuilder("insert into testdatabase.WebAnalytics (domain_name, country, state,city, browser,ip_address)")
			   .append(" values(?,?,?,?,?,?)");
	
	public static final StringBuilder INSERT_PAGE_VIEWS_ROW_QUERY = new StringBuilder("insert into testdatabase.PageViews (pagePath, website_id, daysSinceLastVisit, lastVisitedDate, pageViewCounter, visitors, uniqueVisitors, timeOnPage,averageTimeOnPage)")
	   .append(" values(?,?,?,?,?,?,?,?,?)");
	
	public static final StringBuilder CHECK_REQUEST_FROM_SAME_IP_COUNTRY = new StringBuilder("select id testdatabase.WebAnalytics wa where wa.id = ? and wa.ip_address = ? and wa.country = ?");
	
	public static final StringBuilder CHECK_REQUEST_FROM_SAME_PAGE = new StringBuilder("select pv.pagePath, pv.daysSinceLastVisit, pv.lastVisitedDate, pv.pageViewCounter, pv.visitors, pv.uniqueVisitors testdatabase.PageViews pv where pv.website_id = ? and pv.pagePath = ?");
}
