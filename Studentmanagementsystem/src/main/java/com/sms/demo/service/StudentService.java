package com.sms.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sms.demo.dto.StudentDto;
import com.sms.demo.model.Student;

public interface StudentService  {
	 
	public Boolean saveStudent(StudentDto studentDto , String filename);
	public List<StudentDto> findAll();
	public Boolean deletebyId(String id);
	public StudentDto findbyId(String id);
	public void clearAll();

}
