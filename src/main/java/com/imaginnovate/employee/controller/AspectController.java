package com.imaginnovate.employee.controller;

import com.imaginnovate.employee.dto.EmployeeDTO;
import com.imaginnovate.employee.exception.EmployeeValidationException;
import com.imaginnovate.employee.validator.EmployeeValidator;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectController {

    @Autowired
    private EmployeeValidator employeeValidator;

    @Before("execution(* com.imaginnovate.employee.service.EmployeeServiceImpl.saveEmployee(..)) && args(employeeDTO,..)")
    public void employeeValidationBeforeAdvice(EmployeeDTO employeeDTO) throws EmployeeValidationException{
        employeeValidator.validateEmployee(employeeDTO);
    }
}
