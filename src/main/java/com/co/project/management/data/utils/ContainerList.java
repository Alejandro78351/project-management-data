package com.co.project.management.data.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ContainerList {
	
	private Map<String, Map<String, Double>> outerMap ;
	private String date;
	private Integer countProject;
	
	public ContainerList(String user ) {
		Map<String, Double> innerMap= new HashMap<>();
		outerMap=new HashMap<>();
		outerMap.put(user, innerMap);
	}


	public Map<String, Map<String, Double>> getOuterMap() {
		return outerMap;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}


	public Integer getCountProject() {
		return countProject;
	}


	public void setCountProject(Integer countProject) {
		this.countProject = countProject;
	}

	

}
