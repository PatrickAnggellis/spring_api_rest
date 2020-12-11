package br.com.web.apirest.controller;

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

import br.com.web.apirest.model.Estudante;
import br.com.web.apirest.repository.EstudanteRepository;

@RestController
@RequestMapping(path = "/estudantes")
public class EstudanteController {

    @Autowired
    private EstudanteRepository estudanteRepository;

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> postEstudante(@RequestBody Estudante estudante){
        try {
            return ResponseEntity.ok(estudanteRepository.save(estudante));  
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } 
    }    

    @GetMapping
    public Iterable<Estudante> getEstudantes(){
        return estudanteRepository.findAll();     
    }
    
    @GetMapping(value="/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Estudante> estudante;
        try {
            estudante = estudanteRepository.findById(id);
            return new ResponseEntity<Optional<Estudante>>(estudante, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Optional<Estudante>>(HttpStatus.NOT_FOUND);
        }
    }
       
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEstudante(@PathVariable Long id) {
        try {
            estudanteRepository.delete(estudanteRepository.findById(id).get());
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putEstudante(@PathVariable String id, @RequestBody Estudante estudante){
        try {
            return ResponseEntity.ok(estudanteRepository.save(estudante));              
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    

}