package com.imaginnovate.employee.Validator;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.imaginnovate.employee.dto.EmployeeDTO;
import com.imaginnovate.employee.exception.EmployeeValidationException;
import com.imaginnovate.employee.validator.EmployeeValidator;


public class EmployeeValidatorTest {
    


    @Test
    public void saveEmployeeValidationFailureTestCase() throws Exception, InvocationTargetException {

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeId("");
        employeeDTO.setFirstName("test");
        employeeDTO.setLastName("test");
        employeeDTO.setEmail("test@example.com");
        employeeDTO.setDoj("2023-08-09");
        employeeDTO.setSalary(50000.00);
        employeeDTO.setPhoneNumbers(Arrays.asList("1112223334","2233221122"));

        EmployeeValidator employeeValidator = new EmployeeValidator();

        EmployeeValidationException exception = Assertions.assertThrows(EmployeeValidationException.class,
         () -> {employeeValidator.validateEmployee(employeeDTO);});

         Assertions.assertEquals("Employee ID cannot be null or empty", exception.getMessage());

        employeeDTO.setEmployeeId("E126");
        employeeDTO.setFirstName("");

        exception = Assertions.assertThrows(EmployeeValidationException.class,
         () -> {employeeValidator.validateEmployee(employeeDTO);});

         Assertions.assertEquals("FirstName cannot be null or empty", exception.getMessage());

        employeeDTO.setFirstName("test");
        employeeDTO.setLastName("");

        exception = Assertions.assertThrows(EmployeeValidationException.class,
         () -> {employeeValidator.validateEmployee(employeeDTO);});

         Assertions.assertEquals("LastName cannot be null or empty", exception.getMessage());

        employeeDTO.setLastName("test");
        employeeDTO.setEmail("testexample.com");

        exception = Assertions.assertThrows(EmployeeValidationException.class,
         () -> {employeeValidator.validateEmployee(employeeDTO);});

        Assertions.assertEquals("Invalid email format", exception.getMessage());


        employeeDTO.setEmail("test@example.com");
        employeeDTO.setDoj("2K24-08-09");

        exception = Assertions.assertThrows(EmployeeValidationException.class,
         () -> {employeeValidator.validateEmployee(employeeDTO);});

         Assertions.assertEquals("Date of joining must be a valid date", exception.getMessage());

        employeeDTO.setDoj("2023-08-09");
        employeeDTO.setSalary(-20.00);

        exception = Assertions.assertThrows(EmployeeValidationException.class,
         () -> {employeeValidator.validateEmployee(employeeDTO);});

         Assertions.assertEquals("Salary must be a positive number", exception.getMessage());

        employeeDTO.setSalary(50000.00);
        employeeDTO.setPhoneNumbers(new ArrayList<>());

        exception = Assertions.assertThrows(EmployeeValidationException.class,
         () -> {employeeValidator.validateEmployee(employeeDTO);});

         Assertions.assertEquals("Phone numbers cannot be empty", exception.getMessage());
        
    }

}
