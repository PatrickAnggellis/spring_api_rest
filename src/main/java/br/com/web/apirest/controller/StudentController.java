package br.com.web.apirest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.web.apirest.model.Student;
import br.com.web.apirest.repository.StudentRepository;



@RestController
@RequestMapping(path = "/students")
public class StudentController {

    //private List<Student> students = new ArrayList<>();


    @Autowired
    private StudentRepository studentRepository;

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> postStudent(@RequestBody Student student){
        try {
            return ResponseEntity.ok(studentRepository.save(student));  
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
       
    }    

    @GetMapping
    public Iterable<Student> getStudents(){
        return studentRepository.findAll();     
    }
    
    //Verificar depois como fica esse por conta do Option
    
    @GetMapping(value="/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Student> student;
        try {
            student = studentRepository.findById(id);
            return new ResponseEntity<Optional<Student>>(student, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Optional<Student>>(HttpStatus.NOT_FOUND);
        }
    }
       
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        try {
            studentRepository.delete(studentRepository.findById(id).get());
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putStudent(@PathVariable String id, @RequestBody Student student){
        try {
            return ResponseEntity.ok(studentRepository.save(student));              
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    

}