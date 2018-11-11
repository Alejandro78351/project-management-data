package com.co.project.management.data.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.project.management.data.dto.ResponseMaxHoursProjectDTO;
import com.co.project.management.data.dto.ResponseTotalHoursDTO;
import com.co.project.management.data.service.AppService;

@RestController
@RequestMapping("/api/projectdata")
public class AppController {

	private AppService service;
	
	public AppController(AppService service) {
		this.service=service;
	}

	@GetMapping("/totalhours")
	public  ResponseEntity<ResponseTotalHoursDTO> getTotalHours() {
		ResponseTotalHoursDTO respDTO=service.getTotalHourrs();
		return ResponseEntity.ok(respDTO);
	}
	
	@GetMapping("/maxprojecthours")
	public  ResponseEntity<ResponseMaxHoursProjectDTO> getProjectMaxHours() {
		ResponseMaxHoursProjectDTO respDTO=service.getProjectMaxHours();
		return ResponseEntity.ok(respDTO);
	}
}
