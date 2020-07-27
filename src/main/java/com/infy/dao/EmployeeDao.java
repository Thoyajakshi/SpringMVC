package com.infy.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.infy.exception.EmployeeException;
import com.infy.model.Address;
import com.infy.model.Employee;

@Repository
public class EmployeeDao {

	@Autowired
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
					this.template = template;
		
		
	}

	public int save(Employee p) throws EmployeeException{
		String sql = "insert into emp(name,salary,designation) values('" + p.getName() + "'," + p.getSalary() + ",'"
				+ p.getDesignation() + "')";
		try {
		return template.update(sql);
		} catch(Exception e) {
			throw new EmployeeException ("Error while inserting Employee");
		}
	}

	public int saveaddress(Address a) throws EmployeeException{
		String sql = "insert into address(id,city,state,pincode) values('" + a.getId() + "'," + a.getCity() + ",'"
				+ a.getState() + "'," + a.getPincode() + "')";
		try {
		return template.update(sql);
		} catch(Exception e) {
			throw new EmployeeException ("Error while inserting");
		}
	}

	public int update(Employee p) throws EmployeeException{
		String sql = "update emp set name='" + p.getName() + "', salary=" + p.getSalary() + ",designation='"
				+ p.getDesignation() + "' where id=" + p.getId() + "";
		try {
		return template.update(sql);
		} catch(Exception e) {
			throw new EmployeeException ("Error while updating");
		}
	}

	public int delete(int id) throws EmployeeException{
		String sql = "delete from emp where id=" + id + "";
		try {
		return template.update(sql);
		} catch(Exception e) {
			throw new EmployeeException ("Error while deleting");
		}
	}

	public Employee getEmpById(int id) throws EmployeeException{  
	    //String sql="select * from emp where id=?"; 
		String sql = "select emp.id, emp.name, emp.salary, emp.designation, address.city, address.state, address.pincode from emp inner join address on emp.id=address.id where id=?";
		try {
		return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Employee>(Employee.class));  
		} catch(Exception e) {
			throw new EmployeeException ("Error while fetcting");
		}
	}

	public List<Employee> getEmployees() throws EmployeeException{
		//return template.query("select * from emp", new RowMapper<Employee>() {
		try {
		return template.query("select emp.id, emp.name, emp.salary, emp.designation, address.city, address.state, address.pincode from emp full outer join address on emp.id=address.id", new RowMapper<Employee>() {
			public Employee mapRow(ResultSet rs, int row) throws SQLException {
				Employee e = new Employee();
				 List<Address> l = new ArrayList<Address>();
				 Address address = new Address();
				 
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setSalary(rs.getFloat(3));
				e.setDesignation(rs.getString(4));
				
				address.setCity(rs.getString(5));
				 address.setState(rs.getString(6));
				 address.setPincode(rs.getString(7));
				 
				 l.add(address);
				 e.setAddress(l);
				return e;
			}	
		});
		} catch(Exception e) {
			throw new EmployeeException ("Error while fetcting");
		}
}
}
