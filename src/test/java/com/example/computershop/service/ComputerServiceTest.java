package com.example.computershop.service;

import com.example.computershop.exception.ProductNotFoundException;
import com.example.computershop.model.Computer;
import com.example.computershop.model.enums.FormFactor;
import com.example.computershop.repository.ComputerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.example.computershop.model.enums.FormFactor.*;

@ExtendWith(MockitoExtension.class)
class ComputerServiceTest {

    @Mock
    private ComputerRepository computerRepository;

    @InjectMocks
    private ComputerService computerService;

    Computer comp1;
    Computer comp2;
    Computer comp3;
    List<Computer> comps;
    @BeforeEach
    public void setup() {
        int id1 = 1, id2 = 2, id3 = 3;
        int sn1 = 11, sn2 = 22, sn3 = 33;
        String manufacturer1 = "LG", manufacturer2 = "Asus", manufacturer3 = "Sony";
        double price1 = 100, price2 = 200, price3 = 300;
        int quantity1 = 7, quantity2 = 8, quantity3 = 9;
        FormFactor type1 = monoblock, type2 = nettop, type3 = desktop;

        comp1 = new Computer(id1, sn1, manufacturer1, price1, quantity1, type1);
        comp2 = new Computer(id2, sn2, manufacturer2, price2, quantity2, type2);
        comp3 = new Computer(id3, sn3, manufacturer3, price3, quantity3, type3);
        comps = new ArrayList<>(List.of(comp1, comp2, comp3));
    }

    // Tests on method addComputer
    @Test
    public void addCompPositiveTest() {
        Mockito.when(computerRepository.save(comp1)).thenReturn(comp1);
        Mockito.when(computerRepository.findAll()).thenReturn(Collections.emptyList());
        Assertions.assertThat(comp1).isEqualTo(computerService.add(comp1));
    }
    @Test
    public void addCompPositiveTest2() {
        Mockito.when(computerRepository.save(comp1)).thenReturn(comp1);
        Mockito.when(computerRepository.findAll()).thenReturn(comps);
        Assertions.assertThat(comp1).isEqualTo(computerService.add(comp1));
    }
    @Test
    public void addCompNullNegativeTest() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(()->computerService.add(null));
    }

    // Tests on method editComputer
    @Test
    public void editCompPositiveTest() {
        Mockito.when(computerRepository.save(comp1)).thenReturn(comp1);
        Mockito.when(computerRepository.findById(1)).thenReturn(Optional.of(comp1));
        Assertions.assertThat(comp1).isEqualTo(computerService.edit(1, comp1));
    }

    @Test
    public void editCompNullNegativeTest() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(()->computerService.edit(1,null));
    }
    @Test
    public void editCompWhenOptionalNullNegativeTest() {
        Mockito.when(computerRepository.findById(1)).thenReturn(Optional.empty());
        Assertions.assertThatExceptionOfType(ProductNotFoundException.class)
                .isThrownBy(()->computerService.edit(1,comp1));
    }

    // Tests on method findByIdComputer
    @Test
    public void findByIdWhenOptionalNullNegativeTest() {
        Mockito.when(computerRepository.findById(1)).thenReturn(Optional.empty());
        Assertions.assertThatExceptionOfType(ProductNotFoundException.class)
                .isThrownBy(()->computerService.getById(1));
    }
    @Test
    public void findByIdPositiveTest() {
        Mockito.when(computerRepository.findById(1)).thenReturn(Optional.of(comp1));
        Assertions.assertThat(comp1).isEqualTo(computerService.getById(1));
    }

    // Tests on method findAll
    @Test
    public void findAllPositiveTest() {
        Mockito.when(computerRepository.findAll()).thenReturn(comps);
        Assertions.assertThat(comps).isEqualTo(computerService.getAll());
    }

}