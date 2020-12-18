package br.com.web.apirest.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.web.apirest.builder.EstudanteDTOBuilder;
import br.com.web.apirest.dto.EstudanteDTO;
import br.com.web.apirest.exception.EstudanteAlreadyRegisteredException;
import br.com.web.apirest.exception.EstudanteNotFoundException;
import br.com.web.apirest.mapper.EstudanteMapper;
import br.com.web.apirest.model.Estudante;
import br.com.web.apirest.repository.EstudanteRepository;

public class EstudanteServiceTest {

    @Mock
    private EstudanteRepository estudanteRepository;

    @InjectMocks
    private EstudanteService estudanteService;

    @BeforeEach
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void whenEstudanteInformedThenItShouldBeCreated() throws EstudanteAlreadyRegisteredException {

        //given
        EstudanteDTO expectedEstudanteDTO = EstudanteDTOBuilder.builder().build().toEstudanteDTO();
        Estudante expectedSaveEstudante = EstudanteMapper.toModel(expectedEstudanteDTO);

                // when
                when(estudanteRepository.findByName(expectedEstudanteDTO.getName())).thenReturn(Optional.empty());
                when(estudanteRepository.save(expectedSaveEstudante)).thenReturn(expectedSaveEstudante);
        
                //then
                EstudanteDTO createdCoffeeDTO = estudanteService.postEstudante(expectedEstudanteDTO);

                assertThat(createdCoffeeDTO.getIdEstudante(), is(equalTo(expectedEstudanteDTO.getIdEstudante())));
                assertThat(createdCoffeeDTO.getName(), is(equalTo(expectedEstudanteDTO.getName())));
            }
        
            @Test
            void whenAlreadyRegisteredBeerInformedThenAnExceptionShouldBeThrown() {
                //given
                EstudanteDTO expectedEstudanteDTO = EstudanteDTOBuilder.builder().build().toEstudanteDTO();
                Estudante duplicatedEstudante = EstudanteMapper.toModel(expectedEstudanteDTO);
        
                //when
                when(estudanteRepository.findByName(expectedEstudanteDTO.getName())).thenReturn(Optional.of(duplicatedEstudante));
        
                //then
                assertThrows(EstudanteAlreadyRegisteredException.class, () -> EstudanteService.postEstudante(expectedEstudanteDTO));
            }
        
            @Test
            void whenValidEstudanteNameIsGivenThenReturnAEstudante() throws EstudanteNotFoundException {
                // given
                EstudanteDTO expectedFoundEstudanteDTO = EstudanteDTOBuilder.builder().build().toEstudanteDTO();
                Estudante expectedFoundEstudante = EstudanteMapper.toModel(expectedFoundEstudanteDTO);
        
                //when
                when(estudanteRepository.findByName(expectedFoundEstudanteDTO.getName())).thenReturn(Optional.of(expectedFoundEstudante));
        
                //then
                EstudanteDTO foundEstudanteDTO = estudanteService.findByNameEstudante(expectedFoundEstudanteDTO.getName());

                assertThat(foundEstudanteDTO, is(equalTo(expectedFoundEstudanteDTO)));
            }

            @Test
            void whenNotRegisteredEstudanteNameIsGivenThenThrowAnException() {
                // given
                EstudanteDTO expectedFoundEstudanteDTO = EstudanteDTOBuilder.builder().build().toCoffeeDTO();

                // when
                when(estudanteRepository.findByName(expectedFoundEstudanteDTO.getName())).thenReturn(Optional.empty());

                // then
                assertThrows(EstudanteNotFoundException.class,
                        () -> estudanteService.findByNameEstudante(expectedFoundEstudanteDTO.getName()));
            }
        
            @Test
            void whenListEstudanteIsCalledThenReturnAnEmptyListOfEstudantes() { 
                //when
                when(estudanteRepository.findAll()).thenReturn(Collections.emptyList());
        
                //then
                List<EstudanteDTO> foundListEstudantesDTO = estudanteService.getEstudantes();
        
                assertThat(foundListEstudantesDTO, is(empty()));
            }
        
            @Test
            void whenExclusionIsCalledWithValidIdThenAEstudanteShouldBeDeleted() throws EstudanteNotFoundException {
                //given
                EstudanteDTO expectedDeletedEstudanteDTO = EstudanteDTOBuilder.builder().build().toEstudanteDTO();
                Estudante expectedDeletedEstudante = EstudanteMapper.toModel(expectedDeletedEstudanteDTO);
        
                //when
                when(estudanteRepository.findById(expectedDeletedEstudanteDTO.getIdEstudante())).thenReturn(Optional.of(expectedDeletedEstudante));
                doNothing().when(estudanteRepository).deleteById(expectedDeletedEstudanteDTO.getIdEstudante());
        
                //then
                estudanteService.deleteEstudante(expectedDeletedEstudante.getIdEstudante());
        
                verify(estudanteRepository, times(1)).findById(expectedDeletedEstudante.getIdEstudante());
                verify(estudanteRepository, times(1)).deleteById(expectedDeletedEstudante.getIdEstudante());
        }
}
