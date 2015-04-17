package com.ca.webanalytics.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="pageViews") 
public class PageViews implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Integer daysSinceLastVisit;
	String pagePath;
	Calendar timeOnPge;
	Calendar avgTimeOnPage;
	Calendar lastVisitedDate;
	Integer pageViewCounter;
	Integer visitors;
	Integer uniqueVisitors;

	@XmlElement(name="daysSinceLastVisit") 
	public Integer getDaysSinceLastVisit() {
		return daysSinceLastVisit;
	}

	public void setDaysSinceLastVisit(Integer daysSinceLastVisit) {
		this.daysSinceLastVisit = daysSinceLastVisit;
	}

	@XmlElement(name="pagePath") 
	public String getPagePath() {
		return pagePath;
	}

	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}

	@XmlElement(name="timeOnPge") 
	public Calendar getTimeOnPge() {
		return timeOnPge;
	}

	public void setTimeOnPge(Calendar timeOnPge) {
		this.timeOnPge = timeOnPge;
	}

	@XmlElement(name="avgTimeOnPage") 
	public Calendar getAvgTimeOnPage() {
		return avgTimeOnPage;
	}

	public void setAvgTimeOnPage(Calendar avgTimeOnPage) {
		this.avgTimeOnPage = avgTimeOnPage;
	}

	@XmlElement(name="lastVisitedDate") 
	public Calendar getLastVisitedDate() {
		return lastVisitedDate;
	}

	public void setLastVisitedDate(Calendar lastVisitedDate) {
		this.lastVisitedDate = lastVisitedDate;
	}
	
	@XmlElement(name="pageViewCounter") 
	public Integer getPageViewCounter() {
		return pageViewCounter;
	}

	public void setPageViewCounter(Integer pageViewCounter) {
		this.pageViewCounter = pageViewCounter;
	}

	@XmlElement(name="visitors") 
	public Integer getVisitors() {
		return visitors;
	}

	public void setVisitors(Integer visitors) {
		this.visitors = visitors;
	}

	@XmlElement(name="uniqueVisitors") 
	public Integer getUniqueVisitors() {
		return uniqueVisitors;
	}

	public void setUniqueVisitors(Integer uniqueVisitors) {
		this.uniqueVisitors = uniqueVisitors;
	}
}
