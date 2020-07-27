package com.infy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.infy.dao.EmployeeDao;
import com.infy.exception.EmployeeException;
import com.infy.model.Address;
import com.infy.model.Employee;

@Controller
public class EmployeeController {

	@Autowired    
    EmployeeDao dao;
	
	/*
	 * @RequestMapping("/employee") public String handler(Model model) { Employee e
	 * = new Employee(); e.setId(1); e.setName("Thoya"); e.setSalary(30000);
	 * e.setDesignation("SE");
	 * 
	 * model.addAttribute("e",e); return "employeelist"; }
	 */
	@RequestMapping("/empform")    
    public String showform(Model m){ 
		//System.out.println("controller");
        m.addAttribute("command", new Employee());  
        return "empform"; 
		
    } 
	
	@RequestMapping("/address")    
    public String showaddressform(Model m){ 
        m.addAttribute("address", new Address());  
        return "address"; 
    } 
	
	@RequestMapping(value="/save",method = RequestMethod.POST)  
    public String save(@ModelAttribute("emp") Employee emp, Model m){  
		try {
        dao.save(emp);  
       // return "redirect:/viewemp";
        return "redirect:/address";
		} catch (EmployeeException e) {
			m.addAttribute("message",e.getMessage());
        	return "error";      	
        }
    }
	
	@RequestMapping(value="/saveaddress",method = RequestMethod.POST)  
    public String save(@ModelAttribute("address") Address address, Model m){  
		try {
        dao.saveaddress(address); 
        return "message";  
        } catch (EmployeeException e) {
        	m.addAttribute("message",e.getMessage());
        	return "error";      	
        }
    }
	
	/*
	 * @RequestMapping("/save") public String save(@ModelAttribute("e") Employee
	 * emp, Model m){ dao.save(emp); m.addAttribute("e", emp); return
	 * "employeelist"; }
	 */
	
	@RequestMapping("/viewemp")  
    public String viewemp(Model m){  
		try {
        List<Employee> list=dao.getEmployees();  
        m.addAttribute("list",list);
        return "viewemp"; 
		} catch (EmployeeException e) {
			m.addAttribute("message",e.getMessage());
        	return "error";      	
        }
    }  
	
	@RequestMapping(value="/editemp/{id}")  
    public String edit(@PathVariable int id, Model m){ 
		try {
        Employee emp=dao.getEmpById(id);  
        m.addAttribute("command",emp);
        return "empeditform"; 
		} catch (EmployeeException e) {
			m.addAttribute("message",e.getMessage());
        	return "error";      	
        }
    } 
	
	 @RequestMapping(value="/editsave",method = RequestMethod.POST)  
	    public String editsave(@ModelAttribute("emp") Employee emp, Model m){ 
		 try {
	        dao.update(emp);  
	        return "redirect:/viewemp";  
		 } catch (EmployeeException e) {
			 m.addAttribute("message",e.getMessage());
	        	return "error";      	
	        }
	    }
	 
	 @RequestMapping(value="/deleteemp/{id}",method = RequestMethod.GET)  
	    public String delete(@PathVariable int id, Model m){ 
		 try {
	        dao.delete(id);  
	        return "redirect:/viewemp"; 
		 } catch (EmployeeException e) {
			 m.addAttribute("message",e.getMessage());
	        	return "error";      	
	        }
	    }
	 
}
