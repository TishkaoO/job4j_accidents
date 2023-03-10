package ru.job4j.accident.controller;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import ru.job4j.accident.MainApp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = MainApp.class)
@AutoConfigureMockMvc
class AccidentControllerTest {
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
        this.mockMvc.perform(get("/formCreate"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accidents/formCreate"));
    }

    @Test
    @WithMockUser
    void wheGetPageFormUpdateByAccidentId() throws Exception {
        int id = 1;
        this.mockMvc.perform(get("/formUpdatу").param("id", String.valueOf(id)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accidents/formUpdate"));
    }
}