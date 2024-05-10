package com.wajih.spring2.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository student;
    public List<Student> getStudents(){
        return student.findAll();
    }

    public Student save(Student s) {
        return student.saveAndFlush(s);
    }

    public Student findById(Long id) {
        return student.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    public void delete(Student s) {
        student.delete(s);
    }
}
