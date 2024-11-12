package com.imaginnovate.employee.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import com.imaginnovate.employee.controller.EmployeeController;
import com.imaginnovate.employee.dto.EmployeeDTO;
import com.imaginnovate.employee.entity.Employee;
import com.imaginnovate.employee.service.EmployeeService;
import com.imaginnovate.employee.service.EmployeeServiceImpl;

public class EmployeeControllerTest {

   @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Test
    public void saveEmployeeTestCase() throws Exception {

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeId("E121");
        employeeDTO.setFirstName("test");
        employeeDTO.setLastName("test");
        employeeDTO.setEmail("test@example.com");
        employeeDTO.setDoj("2023-08-09");
        employeeDTO.setSalary(50000.00);
        employeeDTO.setPhoneNumbers(Arrays.asList("1112223334","2233221122"));

        Employee employee = EmployeeDTO.prepareEmployee(employeeDTO);
        when(employeeService.saveEmployee(employeeDTO)).thenReturn(employee);
        ResponseEntity<Employee> savedEmployee = employeeController.saveEmployee(employeeDTO);
        Assertions.assertEquals(employee, savedEmployee.getBody());        
    }

    
    
    @Test
    public void getEmployeeTaxationTestCase() throws Exception {
        
        Employee employee = new Employee();
        employee.setEmployeeId("E123");
        employee.setFirstName("test");
        employee.setLastName("test");
        employee.setEmail("test@example.com");
        employee.setDoj("2023-05-01");
        employee.setSalary(250000.0);

        Method calculateYearlySalaryMethod = EmployeeServiceImpl.class.getDeclaredMethod("calculateYearlySalary", Employee.class);
        calculateYearlySalaryMethod.setAccessible(true);

        double calculatedYearlySalary = (Double) calculateYearlySalaryMethod.invoke(employeeService, employee);
        assertEquals(2750000.0, calculatedYearlySalary); //Based on the Date of joining is total worked 11 months, so total salary will be 11 * 250000

        Method calculatedTaxDeductionsMethod = EmployeeServiceImpl.class.getDeclaredMethod("getTaxDeductionsResponse", Employee.class);
        calculatedTaxDeductionsMethod.setAccessible(true);

        @SuppressWarnings("unchecked")
        Map<String, Object> calculatedTaxInfo =  (Map<String, Object>) calculatedTaxDeductionsMethod.invoke(employeeService, employee);
        //first 250000 is 0, second 250000 of 5% is 12500, third 500000 of 10% is 50000 , for remaining 1750000 of 20% is 350000
        //total tax will be 12500 + 50000 + 350000 = 412500 
        assertEquals(412500.0, calculatedTaxInfo.get("taxAmount"));

        //cess ammount = 2750000 - 2500000 = 250000 0f 2% = 5000
        assertEquals(5000.0, calculatedTaxInfo.get("cessAmount"));
        
    }
}
