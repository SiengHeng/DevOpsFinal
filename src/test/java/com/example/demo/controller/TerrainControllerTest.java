package com.example.demo.controller;

import com.example.demo.entity.Terrain;
import com.example.demo.service.TerrainService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TerrainController.class)
public class TerrainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TerrainService terrainService;

    @Test
    void getAllTerrains_ShouldReturnList() throws Exception {
        Terrain terrain = Terrain.builder()
                .id(1L)
                .name("Mountain View")
                .pricePerDay(new BigDecimal("100.00"))
                .build();

        when(terrainService.getAllTerrains()).thenReturn(Arrays.asList(terrain));

        mockMvc.perform(get("/api/terrains"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Mountain View"));
    }

    @Test
    void getTerrainById_WhenExists_ShouldReturnTerrain() throws Exception {
        Terrain terrain = Terrain.builder()
                .id(1L)
                .name("Mountain View")
                .build();

        when(terrainService.getTerrainById(1L)).thenReturn(Optional.of(terrain));

        mockMvc.perform(get("/api/terrains/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Mountain View"));
    }

    @Test
    void getTerrainById_WhenNotExists_ShouldReturn404() throws Exception {
        when(terrainService.getTerrainById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/terrains/1"))
                .andExpect(status().isNotFound());
    }
}
