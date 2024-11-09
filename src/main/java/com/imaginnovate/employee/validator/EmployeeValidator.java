package com.imaginnovate.employee.validator;

import com.imaginnovate.employee.dto.EmployeeDTO;
import com.imaginnovate.employee.exception.EmployeeValidationException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class EmployeeValidator {

    private static final Pattern PHONE_PATTERN = Pattern.compile("\\d{10}");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

    public void validateEmployee(EmployeeDTO employee) {
        validateEmployeeId(employee.getEmployeeId());
        validateName(employee.getFirstName(), employee.getLastName());
        validateEmail(employee.getEmail());
        validatePhoneNumbers(employee.getPhoneNumbers());
        validateDoj(employee.getDoj());
        validateSalary(employee.getSalary());
    }

    private static void validateEmployeeId(String employeeId) {
        if (employeeId == null || employeeId.isEmpty()) {
            throw new EmployeeValidationException("Employee ID cannot be null or empty");
        }
    }

    private static void validateName(String firstName, String LastName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new EmployeeValidationException("FirstName cannot be null or empty");
        }

        if (LastName == null || LastName.isEmpty()) {
            throw new EmployeeValidationException("LastName cannot be null or empty");
        }
    }

    private static void validateEmail(String email) {
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new EmployeeValidationException("Invalid email format");
        }
    }

    private static void validatePhoneNumbers(List<String> phoneNumbers) {
        if (phoneNumbers == null || phoneNumbers.isEmpty()) {
            throw new EmployeeValidationException("Phone numbers cannot be empty");
        }
        for (String phoneNumber : phoneNumbers) {
            if (!PHONE_PATTERN.matcher(phoneNumber).matches()) {
                throw new EmployeeValidationException("Invalid phone number format: " + phoneNumber);
            }
        }
    }

    private static void validateDoj(String doj) {
        if (doj == null || doj.isEmpty()) {
            throw new EmployeeValidationException("Date of joining cannot be null");
        }
        if(!isValidDate(doj)){
            throw new EmployeeValidationException("Date of joining must be a valid date");
        }
    }

    private static boolean isValidDate(String dateString) {
        try {
            LocalDate.parse(dateString);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private static void validateSalary(double salary) {
        if (salary <= 0) {
            throw new EmployeeValidationException("Salary must be a positive number");
        }
    }

}
