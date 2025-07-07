package com.sms.demo.service.impl;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.demo.dto.StudentDto;
import com.sms.demo.model.Student;
import com.sms.demo.repository.StudentRepository;
import com.sms.demo.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    
//    @Override
//    public Boolean saveStudent(StudentDto studentDto, String filename) {
//        try {
//            Student student;
//
//            if (studentDto.getId() != 0) {
//                student = studentRepository.findById(studentDto.getId()).orElse(new Student());
//            } else {
//                student = new Student();
//            }
//
//            student.setStudentId(studentDto.getStudentId());
//            student.setName(studentDto.getName());
//            student.setPhoto(filename);
//
//            studentRepository.save(student);
//            return true;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
    @Override
    public Boolean saveStudent(StudentDto studentDto, String filename) {
        try {
            Student student;

            // Check if updating or creating new
            if (studentDto.getId() != null && !studentDto.getId().isEmpty()) {
                student = studentRepository.findById(studentDto.getId()).orElse(new Student());
                student.setId(studentDto.getId()); // Important for MongoDB to retain _id
            } else {
                student = new Student(); // New record
            }

            student.setStudentId(studentDto.getStudentId());
            student.setName(studentDto.getName());
            student.setPhoto(filename);

            studentRepository.save(student);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public List<StudentDto> findAll() {
        List<Student> students = studentRepository.findAll();
      
        return students.stream() 
                .map(this::convertToDto)
                .collect(Collectors.toList());
        
        //convert list to a java stream for functional-style processing
        // For each Student object in the stream, it calls // conv stu to Dto
     // converted StudentDto objects in to a list
        
    }

//    @Override
//    public Boolean deletebyId(Integer id) {
//        try {
//            studentRepository.deleteById(id);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    @Override
//    public StudentDto findbyId(Integer id) {
//        Optional<Student> student = studentRepository.findById(id);
//        return student.map(this::convertToDto).orElse(null);
//    }
    @Override
    public Boolean deletebyId(String id) {
        try {
            studentRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public StudentDto findbyId(String id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.map(this::convertToDto).orElse(null);
    }

    
//    public CategoryDto findCategory(Integer id) {
//        Category category = categoryRepository.findById(id)
//            .orElseThrow(() -> new CategoryNotFoundException("Category not found with id " + id));
//        return categoryToDto(category);
//    }


    @Override
    public void clearAll() {
        studentRepository.deleteAll();
    }

    // Helper method to convert Student entity to StudentDto
    private StudentDto convertToDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setStudentId(student.getStudentId());
        studentDto.setName(student.getName());
        studentDto.setPhoto(student.getPhoto());
        return studentDto;
    }
}

