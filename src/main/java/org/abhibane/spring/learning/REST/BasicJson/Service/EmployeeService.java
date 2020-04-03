package org.abhibane.spring.learning.REST.BasicJson.Service;

import org.abhibane.spring.learning.REST.BasicJson.Model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeService extends CrudRepository<Employee, Integer> {
	
	Iterable<Employee> findByFirstNameAndLastName(String firstName, String lastName);
	Iterable<Employee> findByFirstName(String firstName);
	Iterable<Employee> findByLastName(String lastName);

}
