package br.com.web.apirest;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/studentes")
public class StudentController {

    private List<Student> students = new ArrayList<>();

    public StudentController(){
        students.addAll(List.of(
            new Student("Patrick"),
            new Student("José"),
            new Student("Liz")
        ));
    }

   @GetMapping
    public Iterable<Student> getStudents(){
        return students;
    }

}