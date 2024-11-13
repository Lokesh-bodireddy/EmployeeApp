package com.imaginnovate.employee.repo;

import com.imaginnovate.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
  Employee findByEmail(String email);
}
