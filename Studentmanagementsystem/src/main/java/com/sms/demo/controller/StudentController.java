package com.sms.demo.controller;

import com.sms.demo.dto.StudentDto;
import com.sms.demo.service.StudentService;
import com.sms.demo.util.FileUploadUtil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    
    @GetMapping("/")
    public String showAllStudents(Model model) {
    	List<StudentDto> all = studentService.findAll();
        model.addAttribute("students", all);
        return "student/index"; 
    }

    @GetMapping("/add-student")
    public String showAddStudentForm() {
        return "student/add-student"; 
    }

   
    @PostMapping("/save-student")
    public String saveStudent(@ModelAttribute StudentDto studentDto, @RequestParam("file") MultipartFile file) {
        String filename = FileUploadUtil.saveFile(file); 
        studentService.saveStudent(studentDto, filename);
        return "redirect:/"; 
    }

    @GetMapping("/edit-student/{id}")
    public String showEditStudentForm(@PathVariable("id") String id, Model model) {
    	try {
        StudentDto studentDto = studentService.findbyId(id);
        model.addAttribute("studentDto", studentDto);
        return "student/edit-student";
    	}catch (Exception e) {
			e.printStackTrace();
			return "redirect:/?error=notFounf";
		}
    }
    
    @PostMapping("/edit-student")
    public String editStudent(@ModelAttribute StudentDto studentDto, @RequestParam("file") MultipartFile file) {
        String filename = file.isEmpty() ? studentDto.getPhoto() : FileUploadUtil.saveFile(file); // Use existing filename if no new file is uploaded
        studentService.saveStudent(studentDto, filename);
        return "redirect:/"; 
    }

    @GetMapping("/delete-student/{id}")
    public String deleteStudent(@PathVariable("id") String id) {
        studentService.deletebyId(id);
        return "redirect:/"; 
    }

    @GetMapping("/view-student/{id}")
    public String viewStudent(@PathVariable("id") String id, Model model) {
        StudentDto studentDto = studentService.findbyId(id);
        if (studentDto == null) {
            return "redirect:/students"; // Redirect if student not found
        }
        model.addAttribute("student", studentDto);
        return "student/view-student"; // Thymeleaf template to show details
    }

    @GetMapping("/clear-students")
    public String clearAll() {
    	studentService.clearAll();
		return "redirect:/";
    	
    }
}
