package com.example.demo.Controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.Student;


@RestController
@RequestMapping("/api/v1")
public class StudentController {
	
	
	
	private static final List<Student> li = Arrays.asList(new Student(1,"rahul"),
				  new Student(2,"rahul is greatr"),
				  new Student(3,"ronaldo")
				  );
	
	@GetMapping(path="/{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId){
  	  
		return li.stream()
				   .filter(student-> studentId.equals(student.getStudentId()))
				      .findFirst()
				        .orElseThrow(()-> new IllegalArgumentException("Student "+studentId+"does not exists"))
		             ;
    }
	
	
	
	/*@GetMapping(path="{studentId")
      public Student getStudent(@PathVariable("studentId") Integer studentId){
    	  
		return li.stream()
				   .filter(student-> studentId.equals(student.getStudentId()))
				      .findFirst()
				        .orElseThrow(()-> new IllegalArgumentException("Student "+studentId+"does not exists"))
		             ;
      }*/

}
