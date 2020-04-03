package org.abhibane.spring.learning.REST.Cassandra.Controller;

import java.util.Optional;

import javax.validation.ValidationException;

import org.abhibane.spring.learning.REST.BasicJson.Model.Employee;
import org.abhibane.spring.learning.REST.Cassandra.Service.EmployeeCassandraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

	@RestController
	public class EmployeeCassandraController {

		@Autowired
		EmployeeCassandraService employeeService;

		@PostMapping("/employee")
		Employee create(@RequestBody Employee employee) {
			if (employee.getFirstName() != null && employee.getLastName() != null)
				return employeeService.save(employee);
			else
				throw new NullPointerException("EmployeeCassandra information cannot be null");
		}

		@GetMapping("/employee")
		Iterable<Employee> read() {
			return employeeService.findAll();
		}
		

		@GetMapping("/employee/{id}")
		Optional<Employee> findById(@PathVariable Integer id) {
			return employeeService.findById(id);
		}

		@GetMapping("/employee/search")
		Iterable<Employee> findByQuery(@RequestParam(value = "first", required = false) String firstName,
				@RequestParam(value = "last", required = false) String lastName) {
//			if (firstName != null && lastName != null)
//				return employeeService.findByFirstNameAndLastName(firstName, lastName);
//			else if (firstName != null)
//				return employeeService.findByFirstName(firstName);
//			else if (lastName != null)
//				return employeeService.findByLastName(lastName);
//			else
				return employeeService.findAll();
		}

		@PutMapping("/employee")
		ResponseEntity<Employee> update(@RequestBody Employee employee) {
			if (employeeService.findById(employee.getId()).isPresent())
				return new ResponseEntity<Employee>(employeeService.save(employee), HttpStatus.OK);
			else
				throw new ValidationException("EmployeeCassandra with employeeID: " + employee.getId() + " not present!");
		}

		@DeleteMapping("/employee/{id}")
		void delete(@PathVariable Integer id) {
			employeeService.deleteById(id);
		}
	}