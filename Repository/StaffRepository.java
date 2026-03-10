package com.example.SpringBootProject.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.SpringBootProject.Entity.Staff;



public interface StaffRepository extends JpaRepository<Staff, Integer>{
	
	List<Staff> findBySalaryGreaterThan(double salary);
	
	List<Staff> findByExperienceBetween(int min, int max);
	
	List<Staff> findByProfile(String Profile);
	
	List<Staff>  findByProfileNot(String profile);
	
	Staff findTopByOrderBySalaryDesc();
	
	Staff findTopByOrderBySalaryAsc();
	
	List<Staff> deleteById(int id);

	List<Staff> getByName(String name);

}
