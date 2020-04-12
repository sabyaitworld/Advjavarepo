package com.nt.dto;

import java.io.Serializable;
import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor

public class EmployeeDTO implements Serializable {
	private String ename;
	private String eadd;
	private Date   doj;
	private float basicSalary;
	private int id;
	

}
