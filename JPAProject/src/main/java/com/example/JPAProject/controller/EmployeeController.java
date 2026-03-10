package com.example.JPAProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.JPAProject.entity.Employee;
import com.example.JPAProject.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService service;

    //POST insert
    @PostMapping("/add")
    public Employee add(@RequestBody Employee e){
        return service.save(e);
    }
    //Getting employee by name
    @GetMapping("/names") public List<String> names(){ return service.names(); }

    //Getting total employees
    @GetMapping("/count") public long total(){ return service.total(); }

    //Getting Departments
    @GetMapping("/departments") public List<String> departments(){ return service.departments(); }

    //Getting employee per department
    @GetMapping("/emp-per-dept") public List<Object[]> empDept(){ return service.empPerDept(); }

    //Highest paid Employee
    @GetMapping("/highest") public Employee highest(){ return service.highest(); }

    //Lowest paid employee
    @GetMapping("/lowest") public Employee lowest(){ return service.lowest(); }

    //salary above 20000
    @GetMapping("/salary-above-20000") public List<Employee> salaryAbove(){ return service.salaryAbove(); }

    //avg salary
    @GetMapping("/avg-salary") public Double avg(){ return service.avgSalary(); }

    //Top 5 employee by salary
    @GetMapping("/top5") public List<Employee> top5(){ return service.top5(); }

    //Marketing department
    @GetMapping("/marketing") public List<Employee> marketing(){ return service.marketing(); }

    //Salary between range
    @GetMapping("/salary-between") public List<Employee> salaryBetween(){ return service.salaryBetween(); }

    //Null salary provided
    @GetMapping("/salary-null") public List<Employee> salaryNull(){ return service.salaryNull(); }

    //names starts with J
    @GetMapping("/name-start-j") public List<Employee> startJ(){ return service.nameStartJ(); }

    //Decending Order of salary
    @GetMapping("/salary-desc") public List<Employee> salaryDesc(){ return service.salaryDesc(); }

    //Total salary of employees
    @GetMapping("/total-salary") public Double totalSalary(){ return service.totalSalary(); }

    //Employee having same name
    @GetMapping("/same-names") public List<Object[]> sameNames(){ return service.sameNames(); }

    //location == pune
    @GetMapping("/pune") public List<Employee> pune(){ return service.pune(); }

    //avg salary od dev department
    @GetMapping("/avg-dev") public Double avgDev(){ return service.avgDev(); }
    
    //salary above avg 
    @GetMapping("/above-avg") public List<Employee> aboveAvg(){ return service.aboveAvg(); }

    //lowest salary in test department
    @GetMapping("/lowest-test") public List<Employee> lowestTest(){ return service.lowestTest(); }

    //hired in 2023 , getting the count
    @GetMapping("/hired-2023-count") public long hired2023(){ return service.hired2023(); }

    //hired employee in 2023
    @GetMapping("/hired-2023") public List<Employee> emp2023(){ return service.emp2023(); }

    //salary of dev depart for supporting role
    @GetMapping("/dev-support-salary") public Double devSupport(){ return service.devSupportSalary(); }

    //Greater salary in dev department
    @GetMapping("/greater-dev-avg") public List<Employee> greaterDevAvg(){ return service.greaterDevAvg(); }

    //pune people salary
    @GetMapping("/pune-total-salary") public Double puneSalary(){ return service.puneSalary(); }

}
