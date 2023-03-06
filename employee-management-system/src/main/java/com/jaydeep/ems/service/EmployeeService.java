package com.jaydeep.ems.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaydeep.ems.dto.EmployeeDTO;
import com.jaydeep.ems.entity.Employee;
import com.jaydeep.ems.exception.EmployeeException;
import com.jaydeep.ems.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<EmployeeDTO> getAllEmployees() throws EmployeeException {
		
		List<Employee> employees = employeeRepository.findAll();
		
		if(employees.isEmpty()) {
			throw new EmployeeException("Service.NO_EMPLOYEE_EXISTS");
		}
		
		List<EmployeeDTO> employeeDTOs = new ArrayList<>();
		
		employees.forEach(e ->{
			
			EmployeeDTO employeeDTO = modelMapper.map(e, EmployeeDTO.class);
			employeeDTOs.add(employeeDTO);
		});
		
		return employeeDTOs;
	}

	public EmployeeDTO getEmployeeById(Long id) throws EmployeeException {
		
		Optional<Employee> opt = employeeRepository.findById(id);
		Employee employee = opt.orElseThrow(()-> new EmployeeException("Service.NO_EMPLOYEE_WITH_THIS_ID_"+id));
		
		return modelMapper.map(employee, EmployeeDTO.class);
	}

	public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) throws EmployeeException {
		
		Employee employee = employeeRepository.findByEmail(employeeDTO.getEmail());
		
		if(employee!= null) {
			throw new EmployeeException("Service.NO_EMPLOYEE_WITH_THIS_EMAIL_"+ employeeDTO.getEmail());
		}
		
		Employee emp = modelMapper.map(employeeDTO, Employee.class);
		
		Employee savedEmployee = employeeRepository.save(emp);
		
		return modelMapper.map(savedEmployee, EmployeeDTO.class);
	}

	public String deleteEmployeeById(Long id) throws EmployeeException {
		
		Optional<Employee> opt = employeeRepository.findById(id);
		Employee employee = opt.orElseThrow(()-> new EmployeeException("Service.NO_EMPLOYEE_WITH_THIS_ID_"+id));
		
		employeeRepository.delete(employee);
		
		return "EMPLOYEE_WITH_ID_"+id+"_DELETED_SUCCESSFULLY";
	}

	public String deleteAllEmployees() throws EmployeeException {
		List<Employee> employees = employeeRepository.findAll();
		
		if(employees.isEmpty()) {
			throw new EmployeeException("Service.NO_EMPLOYEE_EXISTS");
		}
		
		employeeRepository.deleteAll();

		return "ALL_EMPLOYEES_DELETED_SUCCESSFULLY";
	}

	public EmployeeDTO updateEmployeeById(Long id, EmployeeDTO employeeDTO) throws EmployeeException {
		
		Optional<Employee> opt = employeeRepository.findById(id);
		Employee employee = opt.orElseThrow(()-> new EmployeeException("Service.NO_EMPLOYEE_WITH_THIS_ID_"+id));
		
		employee.setFirstName(employeeDTO.getFirstName());
		employee.setLastName(employeeDTO.getLastName());
		employee.setEmail(employeeDTO.getEmail());
		employee.setDept(employeeDTO.getDept());
		employee.setSalary(employeeDTO.getSalary());
		
		Employee saved = employeeRepository.save(employee);
		
		return modelMapper.map(saved, EmployeeDTO.class);
	}

}
