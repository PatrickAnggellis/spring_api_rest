package br.com.web.apirest.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.util.Collections;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import br.com.web.apirest.builder.EstudanteDTOBuilder;
import br.com.web.apirest.dto.EstudanteDTO;
import br.com.web.apirest.exception.EstudanteNotFoundException;
import br.com.web.apirest.model.Estudante;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class EstudanteControllerTests {

    private static final String ESTUDANTE_API_URL_PATH = "/api/v1/estudantes";
    private static final long VALID_ESTUDANTE_ID = 1L;
    private static final long INVALID_ESTUDANTE_ID = 2L;
    
    private MockMvc mvc;
    
    @Mock
    private br.com.web.apirest.service.EstudanteService estudanteService;

    @InjectMocks
    private EstudanteController estudanteController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(estudanteController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenPOSTIsCalledWithValidFieldsThenShouldCreateEstudante() throws Exception { 
        //given   
        String createEstudanteRequest = objectMapper.writeValueAsString(objectMapper.readValue(new File("src/test/resources/createEstudanteValidRequest.json"), Estudante.class));
        EstudanteDTO expectedEstudantetDTO = EstudanteDTOBuilder.builder().build().toCoffeeDTO();
        
        //when
        when(estudanteService.postEstudante(expectedEstudantetDTO)).thenReturn(expectedEstudantetDTO);
        
        //then
        mvc.perform(post(ESTUDANTE_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(createEstudanteRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    void whenGETIsCalledThenOkStatusIsReturned() throws Exception {
        // given
        EstudanteDTO estudanteDTO = EstudanteDTOBuilder.builder().build().toEstudanteDTO();

        //when
        when(estudanteService.getEstudantes()).thenReturn(Collections.singletonList(estudanteDTO));

        //then
        mvc.perform(get(ESTUDANTE_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void whenGETIsCalledWithValidDataThenOkStatusIsReturned() throws Exception {
        // given
        EstudanteDTO estudanteDTO = EstudanteDTOBuilder.builder().build().toEstudanteDTO();

        //when
        when(estudanteService.getEstudanteById(estudanteDTO.getIdEstudante())).thenReturn(estudanteDTO);

        //then
        mvc.perform(get(ESTUDANTE_API_URL_PATH + "/" + VALID_ESTUDANTE_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(estudanteDTO.getIdEstudante().intValue())))
                .andExpect(jsonPath("$.name", is(estudanteDTO.getName())));
    }

    @Test
    void whenDELETEIsCalledWithValidIdThenNoContentStatusIsReturned() throws Exception {
        // given
        EstudanteDTO coffeeDTO = EstudanteDTOBuilder.builder().build().toCoffeeDTO();

        // when
        doNothing().when(estudanteService).deleteEstudante(coffeeDTO.getIdEstudante());

        // then
        mvc.perform(delete(ESTUDANTE_API_URL_PATH + "/" + VALID_ESTUDANTE_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void whenDELETEIsCalledWithInvalidIdThenNotFoundStatusIsReturned() throws Exception {
        // when
        doThrow(EstudanteNotFoundException.class).when(estudanteService).deleteEstudante(INVALID_ESTUDANTE_ID);

        // then
        mvc.perform(delete(ESTUDANTE_API_URL_PATH + "/" + INVALID_ESTUDANTE_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenPUTIsCalledWithValidDataThenOKstatusIsReturned() throws Exception {
        // given
        String updateEstudanteValidRequest = objectMapper.writeValueAsString(objectMapper.readValue(new File("src/test/resources/updateEstudanteValidRequest.json"), Estudante.class));
        EstudanteDTO estudanteDTO = EstudanteDTOBuilder.builder().name("Patrick").build().toCoffeeDTO();

        // when
        when(estudanteService.putEstudante(VALID_ESTUDANTE_ID, estudanteDTO)).thenReturn(estudanteDTO);

        // then
        mvc.perform(put(ESTUDANTE_API_URL_PATH + "/" + VALID_ESTUDANTE_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(updateEstudanteValidRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(estudanteDTO.getName())));
    }

    @Test
    void whenPUTIsCalledWithInvalidDataThenNotFoundStatusIsReturned() throws Exception {
        // given
        String updateEstudanteInvalidRequest = objectMapper.writeValueAsString(objectMapper.readValue(new File("src/test/resources/updateEstudanteInvalidRequest.json"), Estudante.class));
        EstudanteDTO estudanteDTO = EstudanteDTOBuilder.builder().id(INVALID_ESTUDANTE_ID).name("Patrick").build().toEstudanteDTO();

        // when
        doThrow(EstudanteNotFoundException.class).when(estudanteService).putEstudante(INVALID_ESTUDANTE_ID, estudanteDTO);

        // then
        mvc.perform(put(ESTUDANTE_API_URL_PATH + "/" + INVALID_ESTUDANTE_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(updateEstudanteInvalidRequest)))
                .andExpect(status().isNotFound());
    }

}