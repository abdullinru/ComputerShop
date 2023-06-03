package com.example.computershop.controller;

import com.example.computershop.model.Computer;
import com.example.computershop.model.FormFactor;
import com.example.computershop.repository.ComputerRepository;
import com.example.computershop.service.ComputerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.example.computershop.model.FormFactor.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ComputerController.class)
class ComputerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @SpyBean
    private ComputerService computerService;
    @MockBean
    private ComputerRepository computerRepository;

    @InjectMocks
    private ComputerController computerController;


    Computer comp1;
    Computer comp2;
    Computer comp3;
    List<Computer> comps;
    int id1, id2, id3;
    int sn1, sn2, sn3;
    String manufacturer1, manufacturer2, manufacturer3;
    double price1, price2, price3;
    int quantity1, quantity2, quantity3;
    FormFactor type1, type2, type3;
    @BeforeEach
    public void setup() {
        id1 = 1;
        id2 = 2;
        id3 = 3;
        sn1 = 11;
        sn2 = 22;
        sn3 = 33;
        manufacturer1 = "LG";
        manufacturer2 = "Asus";
        manufacturer3 = "Sony";
        price1 = 100;
        price2 = 200;
        price3 = 300;
        quantity1 = 7;
        quantity2 = 8;
        quantity3 = 9;
        type1 = monoblock;
        type2 = nettop;
        type3 = desktop;

        comp1 = new Computer(id1, sn1, manufacturer1, price1, quantity1, type1);
        comp2 = new Computer(id2, sn2, manufacturer2, price2, quantity2, type2);
        comp3 = new Computer(id3, sn3, manufacturer3, price3, quantity3, type3);
        comps = new ArrayList<>(List.of(comp1, comp2));
    }

    @Test
    public void getAllPositiveTest() throws Exception{

        Mockito.when(computerRepository.findAll()).thenReturn(comps);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/computer")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(id1))
                .andExpect(jsonPath("$[0].serNomer").value(sn1))
                .andExpect(jsonPath("$[0].manufacturer").value(manufacturer1))
                .andExpect(jsonPath("$[0].quantity").value(quantity1))
                .andExpect(jsonPath("$[0].price").value(price1))
                .andExpect(jsonPath("$[0].type").value(type1.name()))
                .andExpect(jsonPath("$[1].id").value(id2))
                .andExpect(jsonPath("$[1].serNomer").value(sn2))
                .andExpect(jsonPath("$[1].manufacturer").value(manufacturer2))
                .andExpect(jsonPath("$[1].quantity").value(quantity2))
                .andExpect(jsonPath("$[1].price").value(price2))
                .andExpect(jsonPath("$[1].type").value(type2.name()));
    }

    @Test
    public void getByIdPositiveTest() throws Exception{

        Mockito.when(computerRepository.findById(id1)).thenReturn(Optional.of(comp1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/computer/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id1))
                .andExpect(jsonPath("$.serNomer").value(sn1))
                .andExpect(jsonPath("$.manufacturer").value(manufacturer1))
                .andExpect(jsonPath("$.quantity").value(quantity1))
                .andExpect(jsonPath("$.price").value(price1))
                .andExpect(jsonPath("$.type").value(type1.name()));
    }
    @Test
    public void getByIdNegativeTest() throws Exception{

        Mockito.when(computerRepository.findById(id1)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/computer/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void editNegativeTest() throws Exception{

        Mockito.when(computerRepository.findById(id1)).thenReturn(Optional.empty());
        String jsonComp = objectMapper.writeValueAsString(comp1);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/computer/1")
                        .content(jsonComp)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    @Test
    public void editNegativeTestWhenParametrNull() throws Exception{

        Mockito.when(computerRepository.findById(id1)).thenReturn(Optional.of(comp1));
        String jsonComp = objectMapper.writeValueAsString(null);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/computer/1")
                        .content(jsonComp)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void editPositiveTest() throws Exception{

        Mockito.when(computerRepository.findById(id1)).thenReturn(Optional.of(comp1));
        Mockito.when(computerRepository.save(any())).thenReturn(comp2);
        String jsonComp = objectMapper.writeValueAsString(comp2);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/computer/1")
                        .content(jsonComp)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id2))
                .andExpect(jsonPath("$.serNomer").value(sn2))
                .andExpect(jsonPath("$.manufacturer").value(manufacturer2))
                .andExpect(jsonPath("$.quantity").value(quantity2))
                .andExpect(jsonPath("$.price").value(price2))
                .andExpect(jsonPath("$.type").value(type2.name()));
    }

    @Test
    public void addNegativeTestWhenParametrNull() throws Exception{

        String jsonComp = objectMapper.writeValueAsString(null);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/computer")
                        .content(jsonComp)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addPositiveTest() throws Exception{

        String jsonComp = objectMapper.writeValueAsString(comp2);
        Mockito.when(computerRepository.findAll()).thenReturn(Collections.emptyList());
        Mockito.when(computerRepository.save(comp2)).thenReturn(comp2);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/computer")
                        .content(jsonComp)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id2))
                .andExpect(jsonPath("$.serNomer").value(sn2))
                .andExpect(jsonPath("$.manufacturer").value(manufacturer2))
                .andExpect(jsonPath("$.quantity").value(quantity2))
                .andExpect(jsonPath("$.price").value(price2))
                .andExpect(jsonPath("$.type").value(type2.name()));
    }
    @Test
    public void addPositiveTestWhenCompAllreadyExist() throws Exception{

        String jsonComp = objectMapper.writeValueAsString(comp2);
        Mockito.when(computerRepository.findAll()).thenReturn(comps);
        Mockito.when(computerRepository.save(comp2)).thenReturn(comp2);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/computer")
                        .content(jsonComp)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id2))
                .andExpect(jsonPath("$.serNomer").value(sn2))
                .andExpect(jsonPath("$.manufacturer").value(manufacturer2))
                .andExpect(jsonPath("$.quantity").value(quantity2*2))
                .andExpect(jsonPath("$.price").value(price2))
                .andExpect(jsonPath("$.type").value(type2.name()));
    }


}