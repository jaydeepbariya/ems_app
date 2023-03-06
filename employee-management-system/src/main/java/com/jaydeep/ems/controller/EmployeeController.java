package com.jaydeep.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jaydeep.ems.dto.EmployeeDTO;
import com.jaydeep.ems.exception.EmployeeException;
import com.jaydeep.ems.service.EmployeeService;

@RestController
@RequestMapping("employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	//get all
	@GetMapping("")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees() throws EmployeeException{
		
		List<EmployeeDTO> employeeDTOs = employeeService.getAllEmployees();
		
		return new ResponseEntity<List<EmployeeDTO>>(employeeDTOs, HttpStatus.OK);
		
	}
	
	//get one by id
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") Long id) throws EmployeeException{
		
		EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
		
		return new ResponseEntity<EmployeeDTO>(employeeDTO, HttpStatus.OK);
		
	}
	
	
	//add one
	@PostMapping("")
	public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO employeeDTO) throws EmployeeException{
		
		EmployeeDTO employeeDTOSaved = employeeService.addEmployee(employeeDTO);
		
		return new ResponseEntity<EmployeeDTO>(employeeDTOSaved, HttpStatus.CREATED);
		
	}
	
	//update one
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDTO> updateEmployeeById(@PathVariable("id") Long id, 
			@RequestBody EmployeeDTO employeeDTO) throws EmployeeException{
		
		EmployeeDTO employeeDTOUpdated = employeeService.updateEmployeeById(id,employeeDTO);
		
		return new ResponseEntity<EmployeeDTO>(employeeDTOUpdated, HttpStatus.OK);
		
	}
	
	//delete one
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") Long id) throws EmployeeException{
		
		String res = employeeService.deleteEmployeeById(id);
		
		return new ResponseEntity<String>(res, HttpStatus.OK);
		
	}
	
	//delete all
	@DeleteMapping("")
	public ResponseEntity<String> deleteAllEmployees() throws EmployeeException{
		
		String res = employeeService.deleteAllEmployees();
		
		return new ResponseEntity<String>(res, HttpStatus.OK);
		
	}
}
