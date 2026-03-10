package com.example.SpringBootProject.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringBootProject.Entity.Staff;
import com.example.SpringBootProject.Repository.StaffRepository;
import com.example.SpringBootProject.exception.ResourceNotFoundException;

@Service
public class StaffService{

	@Autowired
	public StaffRepository repo;
	
	public List<Staff> getAllStaff() {
		
		return repo.findAll();
	}

	public Staff getStaffById(int id) {
		
		return repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Staff not found on given id"+id));
	}

	public Staff saveStaff(Staff staff) {
		
		return repo.save(staff);
	}

	public List<Staff> salaryMoreThan(double Salary) {
		
		return repo.findBySalaryGreaterThan(Salary);
	}

	public List<Staff> experienceBetween(int min, int max) {
		
		return repo.findByExperienceBetween(min, max);
	}


	public List<Staff> trainerList() {
		
		return repo.findByProfile("Trainer");
	}

	public List<Staff> notTrainerList() {
		
		return repo.findByProfileNot("Trainer");
	}


	public Staff getMaxSalaryStaff() {
		
		return repo.findTopByOrderBySalaryDesc();
	}

	public Staff updateSalary(int id, double salary) {
		
		Staff s = getStaffById(id);
		s.setSalary(salary);
		
		return repo.save(s);
	}

	public List<Staff> deleteById(int id) {
		
		return repo.deleteById(id);
	}

	public List<Staff> getByName(String name) {
		return repo.getByName(name);
	}

	public String getMinimumExperienceStaffName() {
		
		return repo.findTopByOrderBySalaryAsc().getName();
	}

	public void deleteStaff(int id) {
		
		if(!repo.existsById(id)) {
			throw new ResourceNotFoundException("Id didn't exists");
		}
	}

}
