package com.imaginnovate.employee.controller;

import com.imaginnovate.employee.dto.EmployeeDTO;
import com.imaginnovate.employee.entity.Employee;
import com.imaginnovate.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/v1/employees")
    public ResponseEntity<Employee> saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return new ResponseEntity<>(employeeService.saveEmployee(employeeDTO),HttpStatus.CREATED);
    }

    @GetMapping("/v1/employees/{employeeId}/tax-deductions")
    public ResponseEntity<Map<String, Object>> getEmployeeTaxation(@PathVariable String employeeId) {
        return new ResponseEntity<>(employeeService.getEmployeeTaxInfo(employeeId),HttpStatus.OK);
    }
}
