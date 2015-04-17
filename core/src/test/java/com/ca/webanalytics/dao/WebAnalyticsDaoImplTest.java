package com.ca.webanalytics.dao;

import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.ca.webanalytics.entity.PageViews;
import com.ca.webanalytics.entity.WebAnalytics;
import com.ca.webanalytics.entity.WebAnalyticsList;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners( { DependencyInjectionTestExecutionListener.class })
@ContextConfiguration(locations = { "classpath:/trackview-core-context.xml" })
public class WebAnalyticsDaoImplTest {

	@Autowired(required = true)
	WebAnalyticsDao  webAnalyticsDao;
	
	@Test
	public void testPageViewsForGivenSite(){
		WebAnalytics wa = webAnalyticsDao.getSitePageViews(1);
		Assert.assertNotNull(wa.getCountry());
	}
	
	@Test
	public void testPageViewsForGivenSiteAndCountryCode(){
		WebAnalytics sa = webAnalyticsDao.getSitePageViewsForCountryCode(1, "USA");
		Assert.assertNotNull(sa.getCountry());
	}
	
	@Test
	public void testPageViewsForGivenSiteAndDateRange(){
		Calendar fromDate = Calendar.getInstance();
		fromDate.set(2015, 03, 13);
		
		Calendar toDate = Calendar.getInstance();
		toDate.set(2015, 03, 20);

		WebAnalytics sa = webAnalyticsDao.getSitePageViewsForGivenDateRange(1, fromDate.getTime(), toDate.getTime());
		Assert.assertNotNull(sa.getCountry());
	}
	
	@Test
	public void testPageViewsForGivenSiteCountryAndDateRange(){
		Calendar fromDate = Calendar.getInstance();
		fromDate.set(2015, 03, 13);
		
		Calendar toDate = Calendar.getInstance();
		toDate.set(2015, 03, 20);

		WebAnalytics sa = webAnalyticsDao.getSitePageViewsForCountryCodeInDateRange(1, "USA", fromDate.getTime(), toDate.getTime());
		Assert.assertNotNull(sa.getCountry());
	}
	
	@Test
	public void testpopularPageViewsAcrossSites(){
		WebAnalyticsList webAnalyticsCollection = webAnalyticsDao.getPopularPageViews();
		Assert.assertNotNull(webAnalyticsCollection);
		Assert.assertEquals(2, webAnalyticsCollection.getWebAnalyticsList().size());
		for(WebAnalytics webAnalytics : webAnalyticsCollection.getWebAnalyticsList()){
			Assert.assertEquals(1, webAnalytics.getPageViews().size());
		}
	}
	
	@Test
	public void testpopularPageViewsAcrossSitesForPageViewCounterRange(){
		WebAnalyticsList webAnalyticsCollection= webAnalyticsDao.getPageViewsForPageViewRange(1, 8);
		Assert.assertNotNull(webAnalyticsCollection);
		Assert.assertEquals(2, webAnalyticsCollection.getWebAnalyticsList().size());
		for(WebAnalytics webAnalytics : webAnalyticsCollection.getWebAnalyticsList()){
			Assert.assertNotNull(webAnalytics.getCountry());
			for(PageViews pageView : webAnalytics.getPageViews()){
				Assert.assertNotNull(pageView.getPageViewCounter());
			}
		}
	}
	
	
	@Test
	public void testPageViewsAcrossSitesForGivenPageViewCounter(){
		WebAnalyticsList webAnalyticsCollection= webAnalyticsDao.getPageViewsForGivenPageViewCounter(10);
		Assert.assertNotNull(webAnalyticsCollection);
		Assert.assertEquals(2, webAnalyticsCollection.getWebAnalyticsList().size());
		for(WebAnalytics webAnalytics : webAnalyticsCollection.getWebAnalyticsList()){
			Assert.assertNotNull(webAnalytics.getCountry());
			for(PageViews pageView : webAnalytics.getPageViews()){
				Assert.assertNotNull(pageView.getPageViewCounter());
			}
		}
	}
	
	@Test
	public void testpopularPageViewsInCountry(){
		WebAnalyticsList webAnalyticsCollection= webAnalyticsDao.getPopularPageViewsInCountry("USA");
		Assert.assertNotNull(webAnalyticsCollection);
		Assert.assertEquals(2, webAnalyticsCollection.getWebAnalyticsList().size());
		for(WebAnalytics webAnalytics : webAnalyticsCollection.getWebAnalyticsList()){
			Assert.assertNotNull(webAnalytics.getCountry());
			for(PageViews pageView : webAnalytics.getPageViews()){
				Assert.assertNotNull(pageView.getPageViewCounter());
			}
		}
	}
	
	@Test
	public void testInsertWebAnalyticsRow(){
		WebAnalytics webAnalytics = new WebAnalytics();
		webAnalytics.setDomainName("www.yahoo.com");
		webAnalytics.setCountry("USA");
		
		PageViews pageViews =  new PageViews();
		pageViews.setPagePath("test123/page4");
		pageViews.setDaysSinceLastVisit(20);
		pageViews.setUniqueVisitors(5);
		pageViews.setVisitors(1);
		Calendar avgTime = Calendar.getInstance();
		pageViews.setAvgTimeOnPage(avgTime);
		pageViews.setLastVisitedDate(Calendar.getInstance());
		pageViews.setTimeOnPge(Calendar.getInstance());
		pageViews.setPageViewCounter(10);
		
		webAnalytics.addPageViews(pageViews);
		
		webAnalytics= webAnalyticsDao.createPageViewsInDB(webAnalytics);
		Assert.assertNotNull(webAnalytics.getWebsite_id());
	}
}
