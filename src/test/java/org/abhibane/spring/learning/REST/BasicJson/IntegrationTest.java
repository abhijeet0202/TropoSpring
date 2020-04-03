package org.abhibane.spring.learning.REST.BasicJson;

import org.abhibane.spring.learning.REST.BasicJson.Model.Address;
import org.abhibane.spring.learning.REST.BasicJson.Model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class IntegrationTest {
	
	@Test
	public void testCreateReadDelete() {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/employee";
		
		Address address = new Address("Orchid Island", "Gurgaon");
		Employee employee = new Employee(2,"Abhijeet", "Banerjee", 35, true,address);
		
		//POST
		ResponseEntity<Employee> entityResponseEntity = restTemplate.postForEntity(url, employee, Employee.class);
		//GET
		Employee[] employees = restTemplate.getForObject(url, Employee[].class);
		Assertions.assertThat(employees).extracting(Employee::getFirstName).containsOnly("Abhijeet");
		
		//Delete
		restTemplate.delete(url+"/"+entityResponseEntity.getBody().getId());
		Assertions.assertThat(restTemplate.getForObject(url, Employee[].class)).isEmpty();
		
	}

}
