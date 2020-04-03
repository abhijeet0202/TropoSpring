package org.abhibane.spring.learning.REST.Cassandra.Service;

import org.abhibane.spring.learning.REST.BasicJson.Model.Employee;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface EmployeeCassandraService extends CassandraRepository<Employee, Integer>{

}
