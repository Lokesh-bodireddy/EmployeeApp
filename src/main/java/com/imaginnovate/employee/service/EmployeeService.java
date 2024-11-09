package com.imaginnovate.employee.service;

import com.imaginnovate.employee.dto.EmployeeDTO;
import com.imaginnovate.employee.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public interface EmployeeService {

    public Employee saveEmployee(EmployeeDTO employeeDTO);

    public Optional<Employee> getEmployeeById(String employeeId);

    public Map<String, Object> getEmployeeTaxInfo(String employeeId);
}
