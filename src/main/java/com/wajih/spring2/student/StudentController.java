package com.wajih.spring2.student;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@Controller
@RequestMapping(path="/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("getStudents")
    public List<Student> getStudents(){
        return studentService.getStudents();
    }
    @PostMapping("newStudent")
    public ResponseEntity<?> newStudent(@RequestBody Student s){
        try {
            Student savedStudent=studentService.save(s);
            return ResponseEntity.ok(savedStudent);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Cant");
        }
    }
    @DeleteMapping(path = "{StudentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable("StudentId") Long id){
        studentService.delete(studentService.findById(id));
        return ResponseEntity.ok().body("User with id "+id+" Deleted");
    }
    @GetMapping("/thymeleaf")
    public String homePage(Model model) {
        model.addAttribute("stdlist", studentService.getStudents());
        return "index";
    }
    @GetMapping("/thymeleaf/addnew")
    public String addNewStudent(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "newstudent";
    }
    @PostMapping("/thymeleaf/save")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.save(student);
        return "redirect:/thymeleaf";
    }
    @GetMapping("/thymeleaf/deleteStudent/{id}")
    public String deleteStudent(@PathVariable(value = "id") long id) {
        studentService.delete(studentService.findById(id));
        return "redirect:/thymeleaf";
    }
}
