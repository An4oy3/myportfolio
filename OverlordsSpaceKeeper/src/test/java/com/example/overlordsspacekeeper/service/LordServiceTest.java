package com.example.overlordsspacekeeper.service;

import com.example.overlordsspacekeeper.controller.LordController;
import com.example.overlordsspacekeeper.data.dto.request.LordRequest;
import com.example.overlordsspacekeeper.data.dto.response.LordResponse;
import com.example.overlordsspacekeeper.data.dto.response.StatusResponse;
import com.example.overlordsspacekeeper.data.entity.Lord;
import com.example.overlordsspacekeeper.data.entity.Planet;
import com.example.overlordsspacekeeper.data.repositories.LordRepository;
import com.example.overlordsspacekeeper.data.repositories.PlanetRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class LordServiceTest {

    @Autowired
    private static LordService lordService;
    @Mock
    private static LordRepository lordRepository;
    @Mock
    private PlanetRepository planetRepository;

    private Lord lord;

    @Test
    void addLordSuccessTest() {
        LordRequest request = LordRequest
                .builder()
                .name("TestName")
                .age(666).planet(new Planet())
                .build();
        lord = new Lord();
        lord.setId(1L);
        lord.setName(request.getName());
        lord.setAge(request.getAge());
        lord.setPlanets(new ArrayList<>());
        Mockito.when(lordRepository.save(Mockito.any(Lord.class))).thenReturn(lord);
        ResponseEntity<LordResponse> response = lordService.addLord(request);
        assertEquals("ok", Objects.requireNonNull(response.getBody()).getResult());
        assertEquals("TestName", response.getBody().getName());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    void addLordFailTest(){
        LordRequest requestNameEmpty = LordRequest.builder()
                .name("").build();
        ResponseEntity<LordResponse> responseNameEmpty = lordService.addLord(requestNameEmpty);
        assertEquals("Invalid or empty lord`s name", Objects.requireNonNull(responseNameEmpty.getBody()).getResult());
        assertEquals(HttpStatus.BAD_REQUEST, responseNameEmpty.getStatusCode());


        LordRequest requestWithNameNotMatches = LordRequest.builder()
                .name("!!").build();
        ResponseEntity<LordResponse> responseNameNotMatches = lordService.addLord(requestWithNameNotMatches);
        assertEquals("Invalid or empty lord`s name", Objects.requireNonNull(responseNameNotMatches.getBody()).getResult());
        assertEquals(HttpStatus.BAD_REQUEST, responseNameNotMatches.getStatusCode());
    }

    @Test
    void assignControlFailTest() {
        Mockito.doReturn(null)
                .when(lordRepository)
                .findById(1L);

        ResponseEntity<StatusResponse> response = lordService.assignControl(1L, 1L);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("lord is not found", Objects.requireNonNull(response.getBody()).getResult());
    }

    @Test
    void getAllParasites() {
    }

    @Test
    void getTopYoungestLords() {
    }
}