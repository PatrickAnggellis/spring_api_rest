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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/estudantes")
@Api(value = "Estudante")
public class EstudanteController {

    @Autowired
    private EstudanteRepository estudanteRepository;

    @PostMapping
    @ResponseBody
    @ApiOperation(value = "Cadastra um novo estudante.")
    public ResponseEntity<Estudante> postEstudante(@RequestBody Estudante estudante){
        try {
            return ResponseEntity.ok(estudanteRepository.save(estudante));  
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } 
    }    

    @GetMapping
    @ApiOperation(value = "Recupera todos os estudantes cadastrados.")
    public Iterable<Estudante> getEstudantes(){
        return estudanteRepository.findAll();     
    }
    
    @GetMapping(value="/{id}")
    @ApiOperation(value = "Busca um estudante no banco de dados por seu id.")
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
    @ApiOperation(value = "Deleta um estudante do banco de dados.")
    public ResponseEntity<Estudante> deleteEstudante(@PathVariable Long id) {
        try {
            estudanteRepository.delete(estudanteRepository.findById(id).get());
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Edita um estudante contido no banco")
    public ResponseEntity<?> putEstudante(@PathVariable String id, @RequestBody Estudante estudante){
        try {
            return ResponseEntity.ok(estudanteRepository.save(estudante));              
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    

}