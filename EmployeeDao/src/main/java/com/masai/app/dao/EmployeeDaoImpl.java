package com.masai.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.masai.app.Exception.EmployeeException;
import com.masai.app.bean.Employee;
import com.masai.app.utility.DBUtil;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public String saveEmployeeDetails(Employee employee) {
		String message = "Not saved...!";
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("insert into employee values(?,?,?,?)");
			ps.setInt(1, employee.getEmpId());
			ps.setString(2,employee.getName());
			ps.setString(3, employee.getAddress());
			ps.setInt(4, employee.getSalary());
			
			int x = ps.executeUpdate();
			if(x>0) message = "Inserted....!";
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return message;
	}

	@Override
	public Employee getEmployeeById(int empId) throws EmployeeException {
		
		Employee emp = null;
		
		try (Connection conn = DBUtil.provideConnection()){
			PreparedStatement ps = conn.prepareStatement("select * from employee where empId = ?");
			ps.setInt(1, empId);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				emp = new Employee(rs.getInt("empId"), rs.getString("name"), rs.getString("address"), rs.getInt("salary"));
				
				
			}
			else {
				throw new EmployeeException("Id does not exist");
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return emp;
	}

	@Override
	public List<Employee> getAllEmployees() {
		
		List<Employee> list= new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("select * from employee");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(new Employee(rs.getInt("empId"), rs.getString("name"), rs.getString("address"), rs.getInt("salary")));
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return list;
	}

	@Override
	public String deleteEmployee(int empId) throws EmployeeException {
		
		String message = "Not deleted...!";
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("delete from employee where empId = ?");
			ps.setInt(1, empId);
			
			int x = ps.executeUpdate();
			if(x>0) message = "Deleted....!";
			else {
				throw new EmployeeException("Id does not exist");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return message;
		
		
		
		
	}


}
