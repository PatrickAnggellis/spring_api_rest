package br.com.web.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.web.apirest.dto.EstudanteDTO;
import br.com.web.apirest.exception.EstudanteAlreadyRegisteredException;
import br.com.web.apirest.exception.EstudanteNotFoundException;
import br.com.web.apirest.service.EstudanteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/estudantes")
@Api(value = "Estudante")
@AllArgsConstructor
public class EstudanteController {

    @Autowired
    private EstudanteService estudanteService;

    @GetMapping
    @ApiOperation(value = "Recupera todos os estudantes cadastrados.")
    public List<EstudanteDTO> getEstudantes() {
        return estudanteService.getEstudantes();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Busca um estudante no banco de dados por seu id.")
    public EstudanteDTO getEstudanteById(@PathVariable Long idEstudante) throws EstudanteNotFoundException {
        return estudanteService.getEstudanteById(idEstudante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Cadastra um novo estudante.")
    public EstudanteDTO postEstudante(@RequestBody @Validated EstudanteDTO estudanteDTO) throws EstudanteAlreadyRegisteredException{
        return estudanteService.postEstudante(estudanteDTO);
    }    

    @PutMapping("/{id}")
    @ApiOperation(value = "Edita um estudante contido no banco")
    public EstudanteDTO putEstudante(@PathVariable Long idEstudante, @RequestBody @Validated EstudanteDTO estudanteDTO) throws EstudanteAlreadyRegisteredException, EstudanteNotFoundException {
        return estudanteService.putEstudante(idEstudante, estudanteDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Deleta um estudante do banco de dados.")
    public void deleteEstudante(@PathVariable Long idEstudante) throws EstudanteNotFoundException{
        estudanteService.deleteEstudante(idEstudante);
    }

   
    

}