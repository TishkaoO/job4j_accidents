package ru.job4j.accident.controller;

import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import ru.job4j.accident.MainApp;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = MainApp.class)
@AutoConfigureMockMvc
class AccidentControllerTest {

    @MockBean
    private AccidentService accidentService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void whenGetPageAllAccidents() throws Exception {
        this.mockMvc.perform(get("/allAccidents"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accidents/listAccident"));

    }

    @Test
    @WithMockUser
    void whenGetPageFormCreate() throws Exception {
        this.mockMvc.perform(get("/create"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accidents/formCreate"));
    }

    @Test
    @WithMockUser
    void wheGetPageFormUpdateByAccidentId() throws Exception {
        String id = "1";
        Mockito.when(accidentService.findById(1)).thenReturn(Optional.of(new Accident()));
        this.mockMvc.perform(get("/update/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accidents/formUpdate"));
    }
}
