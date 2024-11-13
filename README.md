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
  #Register the user with following API using Post Method
  http://localhost:8080/api/v1/employees

  #Login the user with following API using Post Method, it will returns the generated token. replace the userEmail with user registered mail.
  http://localhost:8080/api/v1/auth/login?userEmail={UserEmail}&password=password

  #To verify the taxation details with following API using Get Method 
  http://localhost:8080/api/v1/employees/{employeeId}/tax-deductions
  take note: This url requires authencation info, for this we need to setup the above generated token in following way, if you not setup , you will not able to access this page.
            Goto Authorization tab -> select "Bearer Token" type -> insert the token into the text box.



