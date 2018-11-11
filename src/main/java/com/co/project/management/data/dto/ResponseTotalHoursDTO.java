package com.co.project.management.data.dto;

import java.util.LinkedList;

import com.co.project.management.data.utils.ContainerList;

public class ResponseTotalHoursDTO {

 private LinkedList<ContainerList> linkedlist;
 
 public ResponseTotalHoursDTO() {
	 
 }


public LinkedList<ContainerList> getLinkedlist() {
	return linkedlist;
}

public void setLinkedlist(LinkedList<ContainerList> linkedlist) {
	this.linkedlist = linkedlist;
}



 
 
}

