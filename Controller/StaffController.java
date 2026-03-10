//package com.example.SpringBootProject.Controller;
//
//import java.util.List;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.SpringBootProject.Entity.Staff;
//import com.example.SpringBootProject.Service.StaffService;
//
//@RestController
//@RequestMapping("/staff")
//public class StaffController {
//	
//	@Autowired
//	StaffService service ;
//	
//	@GetMapping("/all")
//	public List<Staff> getAll(){
//		return service.getAllStaff();
//	}
//	
//	@GetMapping("/{id}")
//	public Staff getById(@PathVariable int id) {
//		return service.getStaffById(id);	
//	}
//	
//	@PostMapping
//	public Staff insert(@RequestBody Staff staff) {
//		return service.saveStaff(staff);
//	}
//	
//	@GetMapping("/salary/{amount}")
//	public List<Staff> salaryMore(@PathVariable double amount){
//		return service.salaryMoreThan(amount);
//	}
//	
//	@GetMapping("/experience")
//	public List<Staff> experienceBetween(@RequestParam int min,@RequestParam int max){
//		return service.experienceBetween(min, max);
//	}
//	
//	@GetMapping("/maxsalary")
//	public Staff maxSalary(){
//		return service.getMaxSalaryStaff();
//	}
//	
//	@PutMapping("/{id}/{salary}")
//	public Staff updateSalary(@PathVariable int id , @PathVariable double salary) {
//		return service.updateSalary(id, salary);
//	}
//	
//	@GetMapping("/minexperience")
//	public String minExperience() {
//		return service.getMininumExperienceSatffName();
//	}
//	
//	@GetMapping("/trainer")
//	public List<Staff> trainer(){
//		return service.trainerList();
//	}
//	
//	@GetMapping("/nottrainer")
//	public List<Staff> notTrainer(){
//		return service.notTrainerList();
//	}
//	
//	@DeleteMapping("/delete/{id}")
//	public List<Staff> delete(@PathVariable int id) {
//		return service.deleteById(id);
//	}
//	
//	@GetMapping("/getbyname/{name}")
//	public List<Staff> getByName(@PathVariable String name) {
//		return service.getByName(name);
//	}
//}


package com.example.SpringBootProject.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.SpringBootProject.Entity.Staff;
import com.example.SpringBootProject.Service.StaffService;
import com.example.SpringBootProject.response.ApiResponse;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService service;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Staff>>> getAllStaff(){

        List<Staff> staffList = service.getAllStaff();

        ApiResponse<List<Staff>> response =
                new ApiResponse<List<Staff>>(200,"Staff fetched successfully",staffList);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Staff>> getStaffById(@PathVariable int id){

        Staff staff = service.getStaffById(id);

        ApiResponse<Staff> response =
                new ApiResponse<>(200,"Staff found",staff);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Staff>> saveStaff(@RequestBody Staff staff){

        Staff saved = service.saveStaff(staff);

        ApiResponse<Staff> response =
                new ApiResponse<>(201,"Staff Inserted successfully",saved);

        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    
    @GetMapping("/salary/{amount}")
    public ResponseEntity<ApiResponse<List<Staff>>> salaryMore(@PathVariable double amount){

        List<Staff> staff = service.salaryMoreThan(amount);

        ApiResponse<List<Staff>> response =
                new ApiResponse<List<Staff>>(200, "Staff fetched successfully", staff);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
 // Experience Between
    @GetMapping("/experience")
    public ResponseEntity<ApiResponse<List<Staff>>> experienceBetween(
            @RequestParam int min,
            @RequestParam int max) {

        List<Staff> staff = service.experienceBetween(min, max);

        ApiResponse<List<Staff>> response =
                new ApiResponse<>(200, "Staff between experience fetched", staff);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Max Salary
    @GetMapping("/maxsalary")
    public ResponseEntity<ApiResponse<Staff>> maxSalary() {

        Staff staff = service.getMaxSalaryStaff();

        ApiResponse<Staff> response =
                new ApiResponse<>(200, "Staff with maximum salary", staff);

        return ResponseEntity.ok(response);
    }

    // Update Salary
    @PutMapping("/{id}/salary")
    public ResponseEntity<ApiResponse<Staff>> updateSalary(
            @PathVariable int id,
            @RequestParam double salary) {

        Staff staff = service.updateSalary(id, salary);

        ApiResponse<Staff> response =
                new ApiResponse<>(200, "Salary updated successfully", staff);

        return ResponseEntity.ok(response);
    }

    // Minimum Experience Staff Name
    @GetMapping("/minexperience")
    public ResponseEntity<ApiResponse<String>> minExperience() {

        String name = service.getMinimumExperienceStaffName();

        ApiResponse<String> response =
                new ApiResponse<>(200, "Minimum experience staff", name);

        return ResponseEntity.ok(response);
    }

    // Trainer List
    @GetMapping("/trainer")
    public ResponseEntity<ApiResponse<List<Staff>>> trainer() {

        List<Staff> trainers = service.trainerList();

        ApiResponse<List<Staff>> response =
                new ApiResponse<>(200, "Trainer list fetched", trainers);

        return ResponseEntity.ok(response);
}
 // Trainer List
    @GetMapping("/nottrainer")
    public ResponseEntity<ApiResponse<List<Staff>>> nottrainer() {

        List<Staff> trainers = service.notTrainerList();

        ApiResponse<List<Staff>> response =
                new ApiResponse<>(200, "Different ", trainers);

        return ResponseEntity.ok(response);
}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteStaff(@PathVariable int id){

        service.deleteStaff(id);

        ApiResponse<String> response =
                new ApiResponse<>(200, "Staff deleted successfully", null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
