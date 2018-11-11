package com.co.project.management.data.service;

import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.co.project.management.data.dao.FileDAO;
import com.co.project.management.data.dto.ResponseMaxHoursProjectDTO;
import com.co.project.management.data.dto.ResponseTotalHoursDTO;
import com.co.project.management.data.utils.ContainerList;

@Service
public class AppService {

	private FileDAO fileDao;
	public AppService(FileDAO fileDao) {
		this.fileDao=fileDao;
	}
	
	public ResponseTotalHoursDTO getTotalHourrs() {
		ResponseTotalHoursDTO resp=new ResponseTotalHoursDTO();
		resp.setLinkedlist(fileDao.getListContainers());
		return resp;
	}
	
	public ResponseMaxHoursProjectDTO getProjectMaxHours() {
		ResponseMaxHoursProjectDTO resp=new ResponseMaxHoursProjectDTO();
		 Map<String, Double> mapProjects =fileDao.getMapProjects();
		Entry<String, Double> entry= mapProjects.entrySet().stream().max(Map.Entry.comparingByValue()).get();
		resp.setProject(entry.getKey());
		resp.setHours(entry.getValue());
		return resp;
	}
}
