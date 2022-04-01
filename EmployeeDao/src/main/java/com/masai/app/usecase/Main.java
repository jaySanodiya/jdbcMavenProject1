package com.masai.app.usecase;

import java.util.List;
import java.util.Scanner;

import com.masai.app.Exception.EmployeeException;
import com.masai.app.bean.Employee;
import com.masai.app.dao.EmployeeDao;
import com.masai.app.dao.EmployeeDaoImpl;

public class Main {
	public static void main(String[] args) {
		
		// passiing employee object to insert into employee table
		
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		
	
		
		String result1 = employeeDao.saveEmployeeDetails(new Employee(1000,"jay","seoni",80000));
		
		System.out.println(result1); // this will print message weather it is inserted or not
		
		
		
		// get employee details by providing Id
		
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Employee Id..");
		
		
		try {
			 Employee emp =  employeeDao.getEmployeeById(sc.nextInt());
			 
			 
			 System.out.println(emp.getEmpId());
			 System.out.println(emp.getName());
			 System.out.println(emp.getAddress());
			 System.out.println(emp.getSalary());
		} catch (EmployeeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		// get all emloyee details
		

		
		List<Employee> students= employeeDao.getAllEmployees();
		
		students.forEach(emp -> {
			

			 System.out.println(emp.getEmpId());
			 System.out.println(emp.getName());
			 System.out.println(emp.getAddress());
			 System.out.println(emp.getSalary());
			System.out.println("===========================");
			
			
		});
		
		
		// delete row of given employee id
		
		System.out.println("Enter Employee Id..");
		
		try {
			String result2 = employeeDao.deleteEmployee(sc.nextInt());
			
			System.out.println(result2);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
	}

}















