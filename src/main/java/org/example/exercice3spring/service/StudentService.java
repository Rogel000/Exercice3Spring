package org.example.exercice3spring.service;

import org.example.exercice3spring.model.Student;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;



@Service
public class StudentService {
    private final Map<UUID, Student> students;

    public StudentService() {
        students = new HashMap<>();

        Student studentA = Student.builder()
                .id(UUID.randomUUID())
                .name("Rogé")
                .firstName("Lucie")
                .age(29)
                .email("email@example.com")
                .build();

        students.put(studentA.getId(), studentA);
    }

    public List<Student> getStudentList( String name) {
        if ( name.isEmpty()) {
            return students.values().stream().toList();
        }
        return students.values().stream().filter(s -> s.getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }

    public Student getStudendById(UUID id) {
        return students.get(id);
    }



    public void addStudent(Student student) {
        students.put(student.getId(), student);

    }

    public void deleteStudent(UUID id) {
        students.remove(id);
    }
    public void updateStudent(Student student) {
        students.put(student.getId(), student);
    }

}
