package com.nt.bo;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class EmployeeBO {
	private String ename;
	private String eadd;
	private  Date  doj;
	private float basicSalary;
	private float grossSalary;
	private float netSalary;
	
}
