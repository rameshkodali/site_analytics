package com.ca.webanalytics.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="webAnalytics") 
public class WebAnalytics implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Integer website_id;
	String domainName;
	String country;
	String state;
	String city;
	String browser;
	String ipAddress;
	List<PageViews> pageViews;

	@XmlElement(name="website_id")  
	public Integer getWebsite_id() {
		return website_id;
	}

	public void setWebsite_id(Integer website_id) {
		this.website_id = website_id;
	}
	
	@XmlElement(name="country")  
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@XmlElement(name="state")  
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@XmlElement(name="city")  
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@XmlElement(name="browser")  
	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	@XmlElement(name="ipAddress")  
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@XmlElement(name="pageViews")  
	public List<PageViews> getPageViews() {
		if (this.pageViews == null) {
			this.pageViews = new ArrayList<PageViews>();
		}
		return pageViews;
	}

	public void addPageViews(PageViews pageViews) {
		if(this.pageViews == null) {
			this.pageViews = new ArrayList<PageViews>();
		}
		
		this.pageViews.add(pageViews);
	}
	
	public void setPageViews(List<PageViews> pageViews) {
		this.pageViews = pageViews;
	}

	@XmlElement(name="domainName")  
	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
}
