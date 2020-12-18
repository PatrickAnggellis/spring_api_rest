package br.com.web.apirest.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.web.apirest.dto.EstudanteDTO;
import br.com.web.apirest.exception.EstudanteAlreadyRegisteredException;
import br.com.web.apirest.exception.EstudanteNotFoundException;
import br.com.web.apirest.mapper.EstudanteMapper;
import br.com.web.apirest.model.Estudante;
import br.com.web.apirest.repository.EstudanteRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EstudanteService {

    private static EstudanteRepository estudanteRepository;

    // Lista todos os estudantes
    public List<EstudanteDTO> getEstudantes() {
        return estudanteRepository.findAll().stream().map(EstudanteMapper::toDTO).collect(Collectors.toList());
    }

    // Procura um aluno por seu ID
    public EstudanteDTO getEstudanteById(Long idEstudante) throws EstudanteNotFoundException {
        Estudante foundEstudante = estudanteRepository.findById(idEstudante)
                .orElseThrow(() -> new EstudanteNotFoundException(idEstudante));

        return EstudanteMapper.toDTO(foundEstudante);
    }

    // Procura um estudante por seu nome
    public EstudanteDTO findByNameEstudante(String name) throws EstudanteNotFoundException {
        Estudante foundEstudante = (Estudante) EstudanteRepository.findByName(name)
                .orElseThrow(() -> new EstudanteNotFoundException(name));

        return EstudanteMapper.toDTO(foundEstudante);
    }

    // Inseri um estudante
    public static EstudanteDTO postEstudante(@Validated EstudanteDTO estudanteDTO)
            throws EstudanteAlreadyRegisteredException {

        verifyIfAlreadyRegistered(estudanteDTO.getName());
        Estudante estudante = EstudanteMapper.toModel(estudanteDTO);
        Estudante savedEstudante = estudanteRepository.save(estudante);
        return EstudanteMapper.toDTO(savedEstudante);
    }

    private static void verifyIfAlreadyRegistered(String name) throws EstudanteAlreadyRegisteredException {
        Optional<Estudante> optSavedEstudante = EstudanteRepository.findByName(name);
                if (optSavedEstudante.isPresent())
                        throw new EstudanteAlreadyRegisteredException(name);

    }

    // Deleta um estudante
    public void deleteEstudante(Long idEstudante) throws EstudanteNotFoundException{
        Estudante estudanteToDelete = verifyExists(idEstudante);
        estudanteRepository.deleteById(estudanteToDelete.getIdEstudante());
    }

    //Verifica se um estudante existi 
    private Estudante verifyExists(Long idEstudante) throws EstudanteNotFoundException{
        return estudanteRepository.findById(idEstudante).orElseThrow(() -> new EstudanteNotFoundException(idEstudante));
    }

    // Atualiza um estudante
    public EstudanteDTO putEstudante(Long idEstudante, EstudanteDTO estudanteDTO)
            throws EstudanteNotFoundException, EstudanteAlreadyRegisteredException {

        Estudante estudanteToUpdate = verifyExists(idEstudante);
        verifyIfAlreadyRegistered(estudanteDTO.getName());
        Estudante savedEstudante = estudanteRepository.save(new Estudante(estudanteToUpdate.getIdEstudante(), estudanteDTO.getName()));
        return EstudanteMapper.toDTO(savedEstudante);
    }

    

}
