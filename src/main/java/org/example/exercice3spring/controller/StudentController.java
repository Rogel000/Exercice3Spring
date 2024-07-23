package org.example.exercice3spring.controller;


import jakarta.validation.Valid;
import org.example.exercice3spring.model.Student;
import org.example.exercice3spring.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public String saveStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add";
        } else {
            if (student.getId() == null) {

                UUID id = UUID.randomUUID();
                student.setId(id);
                studentService.addStudent(student);
            } else {

                studentService.updateStudent(student);
            }
        }

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

    @GetMapping("/teapot")
    public String error() {
        if (true) {
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT);
        }
        return "add";
    }
}
