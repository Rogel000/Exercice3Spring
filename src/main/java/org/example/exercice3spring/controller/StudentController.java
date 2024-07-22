package org.example.exercice3spring.controller;


import org.example.exercice3spring.model.Student;
import org.example.exercice3spring.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String homepage() {
        return "home";
    }

    @GetMapping("/list")
    public String studentsList(@RequestParam(value = "studentName", required = false, defaultValue = "") String name,
                               Model model) {
        List<Student> students = studentService.getStudentList(name);
        model.addAttribute("students", students);
        return "list";
    }

    @GetMapping("/student/{id}")
    public String studentDetails(@PathVariable("id") UUID id, Model model) {
        Student student = studentService.getStudendById(id);
        model.addAttribute("student", student);
        return "details";
    }


    @GetMapping("/add")
    public String addStudent(Model model) {
        model.addAttribute("student", new Student());
        return "add";

    }

    @PostMapping("/add")
    public String saveStudent(@ModelAttribute("student") Student student) {
        UUID id = UUID.randomUUID();
        student.setId(id);
        studentService.addStudent(student);
        System.out.println(student.getId());
        System.out.println(student.getName());
        System.out.println(student.getFirstName());
        System.out.println(student.getAge());
        System.out.println(student.getEmail());
        return "redirect:/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") UUID id) {
        studentService.deleteStudent(id);
        return "redirect:/list";
    }

    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable("id") UUID id, Model model) {
        Student student = studentService.getStudendById(id);
        model.addAttribute("student", student);
        return "add";
    }
}
