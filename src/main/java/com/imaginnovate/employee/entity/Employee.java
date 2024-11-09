package com.imaginnovate.employee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {
        @Id
        private String employeeId;
        private String firstName;
        private String lastName;
        private String email;
        @ElementCollection
        private List<String> phoneNumbers;
        private String doj;
        private Double salary;
}
