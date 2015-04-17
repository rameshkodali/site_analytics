package com.ca.webanalytics.rest;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Calendar;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.ca.webanalytics.entity.PageViews;
import com.ca.webanalytics.entity.WebAnalytics;
import com.ca.webanalytics.entity.WebAnalyticsList;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
@ContextConfiguration(locations = { "classpath:/funcTestContext.xml" })
public class WebAnalyticsRestServiceTest {
	
	private static XLogger LOGGER = XLoggerFactory.getXLogger(WebAnalyticsRestServiceTest.class.getName());

	@Autowired
	private Jaxb2Marshaller marshaller;
	
	/**
	 * Integration test to test the page views for the given site
	 * 
	 */
	@Test
	public void testGetPageAnalyticsForGivenSite()  {
		
		HttpClient httpclient = new HttpClient();
		GetMethod httpGet = new GetMethod("http://localhost:8080/tracking/v1/webanalyticslist/1/webanalytics?operation=pageViewsForSite");
		
		try {
			int result = httpclient.executeMethod(httpGet);

			// Display status code
			LOGGER.error("Response status code: " + result);

			Assert.assertEquals("HTTP Status Code is not correct", 200, result);
			// Display response
			String responseReceived = httpGet.getResponseBodyAsString();
			LOGGER.error("Response body: " + responseReceived);

			WebAnalytics webAnalytics = (WebAnalytics) marshaller.unmarshal(new StreamSource(new StringReader(responseReceived)));
			
			Assert.assertNotNull(webAnalytics);
			
			Assert.assertNotNull(webAnalytics.getDomainName());
			
			for(PageViews pageViews: webAnalytics.getPageViews()){
				Assert.assertNotNull(pageViews.getPageViewCounter());
				Assert.assertNotNull(pageViews.getUniqueVisitors());
			}
			
			
		} catch(Exception e) {
			Assert.fail(e.getMessage());
		} finally {
			httpGet.releaseConnection();
		}
	}

	
	
	/**
	 * Integration test to verify the page views for requested web site in given country
	 * 
	 */
	@Test
	public void testGetSiteAnalyticsForGivenSiteInCountry()  {
		
		HttpClient httpclient = new HttpClient();
		GetMethod httpGet = new GetMethod("http://localhost:8080/tracking/v1/webanalyticslist/1/webanalytics?countryCode=USA&operation=pageViewsForCountry");
		
		try {
			int result = httpclient.executeMethod(httpGet);

			// Display status code
			LOGGER.error("Response status code: " + result);

			Assert.assertEquals("HTTP Status Code is not correct", 200, result);
			// Display response
			String responseReceived = httpGet.getResponseBodyAsString();
			LOGGER.error("Response body: " + responseReceived);

			WebAnalytics webAnalytics = (WebAnalytics) marshaller.unmarshal(new StreamSource(new StringReader(responseReceived)));
			
			Assert.assertNotNull(webAnalytics);
			
			Assert.assertNotNull(webAnalytics.getDomainName());
			
			for(PageViews pageViews: webAnalytics.getPageViews()){
				Assert.assertNotNull(pageViews.getPageViewCounter());
				Assert.assertNotNull(pageViews.getUniqueVisitors());
			}
			
			
		} catch(Exception e) {
			Assert.fail(e.getMessage());
		} finally {
			httpGet.releaseConnection();
		}
	}
	
	/**
	 * Integration test to verify the page views for requested web site in given visited date range
	 * 
	 */
	@Test
	public void testGetSiteAnalyticsForGivenSiteInGivenVisitedDateRange()  {
		
		HttpClient httpclient = new HttpClient();
		GetMethod httpGet = new GetMethod("http://localhost:8080/tracking/v1/webanalyticslist/1/webanalytics?fromDate=2015-04-13&toDate=2015-04-20&operation=pageViewsForDateRange");
		
		try {
			int result = httpclient.executeMethod(httpGet);

			// Display status code
			LOGGER.error("Response status code: " + result);

			Assert.assertEquals("HTTP Status Code is not correct", 200, result);
			// Display response
			String responseReceived = httpGet.getResponseBodyAsString();
			LOGGER.error("Response body: " + responseReceived);

			WebAnalytics webAnalytics = (WebAnalytics) marshaller.unmarshal(new StreamSource(new StringReader(responseReceived)));
			
			Assert.assertNotNull(webAnalytics);
			
			Assert.assertNotNull(webAnalytics.getDomainName());
			
			for(PageViews pageViews: webAnalytics.getPageViews()){
				Assert.assertNotNull(pageViews.getPageViewCounter());
				Assert.assertNotNull(pageViews.getUniqueVisitors());
			}
			
			
		} catch(Exception e) {
			Assert.fail(e.getMessage());
		} finally {
			httpGet.releaseConnection();
		}
	}
	
	/**
	 * Integration test to verify the page views for requested web site in given country in last visited date range
	 * 
	 */
	@Test
	public void testGetSiteAnalyticsForGivenSiteInGivenCountryInVisitedDateRange()  {
		
		HttpClient httpclient = new HttpClient();
		GetMethod httpGet = new GetMethod("http://localhost:8080/tracking/v1/webanalyticslist/1/webanalytics?countryCode=USA&fromDate=2015-04-13&toDate=2015-04-20&operation=pageViewsForCountryInDateRange");
		
		try {
			int result = httpclient.executeMethod(httpGet);

			// Display status code
			LOGGER.error("Response status code: " + result);

			Assert.assertEquals("HTTP Status Code is not correct", 200, result);
			// Display response
			String responseReceived = httpGet.getResponseBodyAsString();
			LOGGER.error("Response body: " + responseReceived);

			WebAnalytics webAnalytics = (WebAnalytics) marshaller.unmarshal(new StreamSource(new StringReader(responseReceived)));
			
			Assert.assertNotNull(webAnalytics);
			
			Assert.assertNotNull(webAnalytics.getDomainName());
			
			for(PageViews pageViews: webAnalytics.getPageViews()){
				Assert.assertNotNull(pageViews.getPageViewCounter());
				Assert.assertNotNull(pageViews.getUniqueVisitors());
			}
			
			
		} catch(Exception e) {
			Assert.fail(e.getMessage());
		} finally {
			httpGet.releaseConnection();
		}
	}
	
	/**
	 * Integration test to get the popular page views across the sites.
	 * 
	 */
	@Test
	public void testGetPopularPageViewsAcrossSites()  {
		
		HttpClient httpclient = new HttpClient();
		GetMethod httpGet = new GetMethod("http://localhost:8080/tracking/v1/webanalyticslist?operation=poularPageViews");
		
		try {
			int result = httpclient.executeMethod(httpGet);

			// Display status code
			LOGGER.error("Response status code: " + result);

			Assert.assertEquals("HTTP Status Code is not correct", 200, result);
			// Display response
			String responseReceived = httpGet.getResponseBodyAsString();
			LOGGER.error("Response body: " + responseReceived);

			WebAnalyticsList webAnalyticsCollection = (WebAnalyticsList) marshaller.unmarshal(new StreamSource(new StringReader(responseReceived)));
			
			Assert.assertNotNull(webAnalyticsCollection);
			
			Assert.assertEquals(2, webAnalyticsCollection.getWebAnalyticsList().size());
			for(WebAnalytics webAnalytics : webAnalyticsCollection.getWebAnalyticsList()){
				Assert.assertEquals(1, webAnalytics.getPageViews().size());
			}
			
		} catch(Exception e) {
			Assert.fail(e.getMessage());
		} finally {
			httpGet.releaseConnection();
		}
	}
	
	/**
	 * Integration test to get the popular page views for given  Page view counter range
	 * 
	 * 
	 */
	@Test
	public void testGetPopularPageViewsForGivenPageViewCounterRange()  {
		
		HttpClient httpclient = new HttpClient();
		GetMethod httpGet = new GetMethod("http://localhost:8080/tracking/v1/webanalyticslist?operation=pageViewsForPageViewCounterRange&pageViewCounterLowerBound=1&pageViewCounterUpperBound=8");
		
		try {
			int result = httpclient.executeMethod(httpGet);

			// Display status code
			LOGGER.error("Response status code: " + result);

			Assert.assertEquals("HTTP Status Code is not correct", 200, result);
			// Display response
			String responseReceived = httpGet.getResponseBodyAsString();
			LOGGER.error("Response body: " + responseReceived);

			WebAnalyticsList webAnalyticsCollection = (WebAnalyticsList) marshaller.unmarshal(new StreamSource(new StringReader(responseReceived)));
			
			Assert.assertNotNull(webAnalyticsCollection);
			
			Assert.assertEquals(2, webAnalyticsCollection.getWebAnalyticsList().size());
			for(WebAnalytics webAnalytics : webAnalyticsCollection.getWebAnalyticsList()){
				Assert.assertNotNull(webAnalytics.getCountry());
				for(PageViews pageView : webAnalytics.getPageViews()){
					Assert.assertNotNull(pageView.getPageViewCounter());
				}
			}
			
		} catch(Exception e) {
			Assert.fail(e.getMessage());
		} finally {
			httpGet.releaseConnection();
		}
	}
	
	/**
	 * Integration test to get the popular page views for given  Page view counter
	 * 
	 * 
	 */
	@Test
	public void testGetPopularPageViewsForGivenPageViewCounter()  {
		
		HttpClient httpclient = new HttpClient();
		GetMethod httpGet = new GetMethod("http://localhost:8080/tracking/v1/webanalyticslist?operation=pageViewsForGivenPageViewCounter&pageViewCounter=10");
		
		try {
			int result = httpclient.executeMethod(httpGet);

			// Display status code
			LOGGER.error("Response status code: " + result);

			Assert.assertEquals("HTTP Status Code is not correct", 200, result);
			// Display response
			String responseReceived = httpGet.getResponseBodyAsString();
			LOGGER.error("Response body: " + responseReceived);

			WebAnalyticsList webAnalyticsCollection = (WebAnalyticsList) marshaller.unmarshal(new StreamSource(new StringReader(responseReceived)));
			
			Assert.assertNotNull(webAnalyticsCollection);
			
			Assert.assertEquals(2, webAnalyticsCollection.getWebAnalyticsList().size());
			for(WebAnalytics webAnalytics : webAnalyticsCollection.getWebAnalyticsList()){
				Assert.assertNotNull(webAnalytics.getCountry());
				for(PageViews pageView : webAnalytics.getPageViews()){
					Assert.assertNotNull(pageView.getPageViewCounter());
				}
			}
			
		} catch(Exception e) {
			Assert.fail(e.getMessage());
		} finally {
			httpGet.releaseConnection();
		}
	}
	
	/**
	 * Integration test to get the popular page views for given country
	 * 
	 * 
	 */
	@Test
	public void testGetPopularPageViewsInCountry()  {
		
		HttpClient httpclient = new HttpClient();
		GetMethod httpGet = new GetMethod("http://localhost:8080/tracking/v1/webanalyticslist?operation=popularPageViewsInCountry&countryCode=USA");
		
		try {
			int result = httpclient.executeMethod(httpGet);

			// Display status code
			LOGGER.error("Response status code: " + result);

			Assert.assertEquals("HTTP Status Code is not correct", 200, result);
			// Display response
			String responseReceived = httpGet.getResponseBodyAsString();
			LOGGER.error("Response body: " + responseReceived);

			WebAnalyticsList webAnalyticsCollection = (WebAnalyticsList) marshaller.unmarshal(new StreamSource(new StringReader(responseReceived)));
			
			Assert.assertNotNull(webAnalyticsCollection);
			
			Assert.assertEquals(2, webAnalyticsCollection.getWebAnalyticsList().size());
			for(WebAnalytics webAnalytics : webAnalyticsCollection.getWebAnalyticsList()){
				Assert.assertNotNull(webAnalytics.getCountry());
				for(PageViews pageView : webAnalytics.getPageViews()){
					Assert.assertNotNull(pageView.getPageViewCounter());
				}
			}
		} catch(Exception e) {
			Assert.fail(e.getMessage());
		} finally {
			httpGet.releaseConnection();
		}
	}
	
	@Test
	public void testCreatePageViewsInDB()  {
		
		HttpClient httpclient = new HttpClient();
		PostMethod httpPost = new PostMethod("http://localhost:8080/tracking/v1/webanalytics");
		
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
		StringWriter writer = new StringWriter();
        StreamResult streamResult = new StreamResult(writer);
        
        marshaller.marshal(webAnalytics, streamResult);
        
		try {
			StringRequestEntity requestEntity =
	        		new StringRequestEntity(writer.toString(), "application/xml", null);
	        httpPost.setRequestEntity(requestEntity);
	        
			int result = httpclient.executeMethod(httpPost);

			// Display status code
			LOGGER.error("Response status code: " + result);
			Assert.assertEquals(200, result);
			
			// Display response
			String responseReceived = httpPost.getResponseBodyAsString();
			LOGGER.error("Response body: " + responseReceived);
			
			webAnalytics = (WebAnalytics) marshaller.unmarshal(new StreamSource(new StringReader(responseReceived)));
			
			Assert.assertNotNull(webAnalytics.getWebsite_id());
			
		} catch(Exception e) {
			LOGGER.catching(e);
			Assert.fail();
		} finally {
			// Release current connection to the connection pool
			httpPost.releaseConnection();
		}
	}
}
