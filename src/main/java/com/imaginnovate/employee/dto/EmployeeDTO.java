package com.imaginnovate.employee.dto;

import com.imaginnovate.employee.entity.Employee;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class EmployeeDTO {

    private String employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private List<String> phoneNumbers;
    private String doj;
    private Double salary;

    public static Employee prepareEmployee(EmployeeDTO employeeDTO){
        Employee emp = new Employee();
        emp.setEmployeeId(employeeDTO.getEmployeeId());
        emp.setFirstName(employeeDTO.getFirstName());
        emp.setLastName(employeeDTO.getLastName());
        emp.setEmail(employeeDTO.getEmail());
        emp.setPhoneNumbers(employeeDTO.getPhoneNumbers());
        emp.setDoj(employeeDTO.getDoj());
        emp.setSalary(employeeDTO.getSalary());
        return emp;
    }
}

