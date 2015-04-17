package com.ca.webanalytics.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "webAnalyticsList")
public class WebAnalyticsList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	List<WebAnalytics> webAnalyticsList = null;

	@XmlElement(name = "webAnalytics")
	public List<WebAnalytics> getWebAnalyticsList() {
		if (this.webAnalyticsList == null) {
			webAnalyticsList = new ArrayList<WebAnalytics>();
		}
		return webAnalyticsList;
	}

	public void addWebAnalyticsList(List<WebAnalytics> webAnalyticsList) {
		if (this.webAnalyticsList == null) {
			this.webAnalyticsList = new ArrayList<WebAnalytics>();
		}
		this.webAnalyticsList.addAll(webAnalyticsList);
	}

	public void setWebAnalyticsList(List<WebAnalytics> webAnalyticsList) {
		this.webAnalyticsList = webAnalyticsList;
	}

}
