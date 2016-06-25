package co.uk.dragosolutions.repository;


import co.uk.dragosolutions.domain.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findByName(String name);

    List<Employee> findByWageBetween(BigInteger low, BigInteger high);
}
