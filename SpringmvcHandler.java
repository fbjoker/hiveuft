package com.alex.springmvc.handler;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.atguigu.springmvc.beans.Department;
import com.atguigu.springmvc.beans.Employee;
import com.atguigu.springmvc.dao.DepartmentDao;
import com.atguigu.springmvc.dao.EmployeeDao;

@Controller
public class SpringmvcHandler {
	@Autowired
	DepartmentDao dd;
	@Autowired
	EmployeeDao ed;
	
	@RequestMapping(value="/emps")
	public String findAllData(Map<String,Object> map) {
		Collection<Employee> all = ed.getAll();
		map.put("emps", all);
		
		
		return "list";
		
	}
	@RequestMapping(value="/emp" ,method=RequestMethod.GET)
	public String toaddpage(Map<String,Object> map) {
		Collection<Department> departments = dd.getDepartments();
		
		map.put("depts", departments);
		
		Map<String, String > gender=new HashMap<String, String>();
		gender.put("o", "nv");
		gender.put("1", "nan");
		
		map.put("genders", gender);
		
		
		Employee employee= new Employee();
		map.put("employee", employee);
		
		
		return "update";
	}
	@RequestMapping(value="/emp", method=RequestMethod.POST)
	public String addData(Employee employee) {
		ed.save(employee);
		
		
		
		return "redirect:/emps";
	}
	@RequestMapping(value="emp/{id}",method=RequestMethod.GET)
	public String toupdatePage(@PathVariable("id")Integer id, Map<String,Object> map) {
Collection<Department> departments = dd.getDepartments();
		
		map.put("depts", departments);
		
		Map<String, String > gender=new HashMap<String, String>();
		gender.put("0", "nv");
		gender.put("1", "nan");
		
		map.put("genders", gender);
		
		
		Employee employee = ed.get(id);
		map.put("employee", employee);
		
		
		
		return "update";
	}
	
	@RequestMapping(value="/emp/{id}", method=RequestMethod.PUT)
	public String updateData(Employee employee) {
		
		ed.save(employee);
		return "redirect:/emps";
	}
	@RequestMapping(value="/emp/{id}", method=RequestMethod.DELETE)
	public String deleteData(@PathVariable("id")Integer id) {
		ed.delete(id);
		
		return "redirect:/emps";
	}

}
