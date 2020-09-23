package com.example.demo.Controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.Student;

@RestController
@RequestMapping("/management/api/v1/")
public class SrudentManagementController {
	

	private static final List<Student> li = Arrays.asList(new Student(1,"rahul"),
				  new Student(2,"rahul is greatr"),
				  new Student(3,"ronaldo")
				  );
	
	@GetMapping
	//@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMIN_TRAINEE')")
	public List<Student> getStudent()
	{
		System.out.println("I am inside get Mapping");
		return li;
	}
	
	@PostMapping
	//@PreAuthorize("hasAuthority('student:write')")
	public void registerStudent(@RequestBody Student stu)
	{
		System.out.println(stu);
	}
	
	@DeleteMapping(path="{studentId}")
	//@PreAuthorize("hasAuthority('student:write')")
    public void deleteStudent(@PathVariable("studentId") Integer studentId)
    {
    	System.out.println(studentId);
    }
	
    @PutMapping(path="{studentId}")
    //@PreAuthorize("hasAuthority('student:write')")
    public void updateStudent(@PathVariable("studentId") Integer studentId,@RequestBody Student stu)
    {
    	System.out.println("Hi, this is put mapping");
    	System.out.println(String.format("%s %s", studentId, stu ));
    }
}
