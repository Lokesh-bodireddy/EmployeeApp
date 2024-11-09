# EmployeeApp

#Steps:
#1. employee_management git 
clone https://github.com/Lokesh-bodireddy/EmployeeApp

#2. Create the database CREATE DATABASE mytestdb;

#3. Modify as per the credentails inside application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/mytestdb 
spring.datasource.username=root 
spring.datasource.password=pas123

#4. Start the Spring Boot Application.

#5. Open the postman tool and use following API end points for testing
http://localhost:8080/api/v1/employees
http://localhost:8080/api/v1/employees/{employeeId}/tax-deductions


