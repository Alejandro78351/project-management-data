package com.co.project.management.data.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.co.project.management.data.utils.ContainerList;

@Component
public class FileDAO {
	
	 private LinkedList<ContainerList> listContainers;
	 private Map<String, Double> mapProjects;
	 
	 DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	 
	@Value("${path.file}")
	private String pathFile;
	
	 @PostConstruct
	public void postConstruct() {
		System.out.println("*************************************************");
		System.out.println("READ FILEEEEEEE");
		System.out.println("*************************************************");
		listContainers=new LinkedList<ContainerList>();
		mapProjects=new HashMap<>();
		readFile();
	}

	public void readFile() {

		try (BufferedReader br = new BufferedReader(new FileReader(pathFile))) {
			br.readLine();
			String sCurrentline;
			String date = "";
			ContainerList container = null;
			Integer cont=0;

			while ((sCurrentline = br.readLine()) != null) {

				String[] array = sCurrentline.split(",");
				String currentDate = array[0];
				String currentUser = array[1];
				String currentProject = array[3];
				String currentHours = array[4];
				if (array.length==6) {
					String currentHour2 = array[5];
					currentHours+="."+currentHour2;
				}
				System.out.println("  ");
				System.out.println("********** currentDate " + currentDate);
				System.out.println("********** date " + date);

				
				if (mapProjects.containsKey(currentProject)) {
					Double hours=mapProjects.get(currentProject);
					hours += Double.parseDouble(currentHours);
					mapProjects.put(currentProject, hours);
					
				}else {
					mapProjects.put(currentProject, Double.parseDouble(currentHours));
				}
				
				if (!currentDate.equals(date)) {
					System.out.println("---------- difffff date------------ " );
				
					cont=1;
					date = currentDate;
					container = new ContainerList(currentUser);
					container.setDate(date);
					Map<String, Map<String, Double>> outerMap = container.getOuterMap();
					Map<String, Double> innerMap = outerMap.get(currentUser);
					innerMap.put(currentProject, Double.parseDouble(currentHours));
					listContainers.push(container);
					System.out.println("---- new Contenier : user  " + currentUser + " project: " +currentProject+ " currentHours : " +currentHours);


				} else {
					Map<String, Map<String, Double>> outerMap = container.getOuterMap();
					if (outerMap.containsKey(currentUser)) {
						if (outerMap.get(currentUser).containsKey(currentProject)) {

							Double hours = outerMap.get(currentUser).get(currentProject);
							System.out.println("---- update USER currenMap hoursss : " + hours + " user : " +currentUser);
							hours += Double.parseDouble(currentHours);
							Map<String, Double> innerMap =outerMap.get(currentUser);
							innerMap.put(currentProject, hours);
							outerMap.put(currentUser, innerMap);
						}else {
							Map<String, Double> innerMap =outerMap.get(currentUser);
							innerMap.put(currentProject, Double.parseDouble(currentHours));
							cont++;
							
						}
						
						
					} else {
						Map<String, Double> innerMap = new HashMap<>();
						innerMap.put(currentProject, Double.parseDouble(currentHours));
						outerMap.put(currentUser, innerMap);
						System.out.println("---- new User CurrenTMAP " + currentUser + " project: " +currentProject+ " currentHours : " +currentHours);
						cont++;

					}

				}
				container.setCountProject(cont);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public LinkedList<ContainerList> getListContainers() {
		return listContainers;
	}

	public Map<String, Double> getMapProjects() {
		return mapProjects;
	}

	
}
