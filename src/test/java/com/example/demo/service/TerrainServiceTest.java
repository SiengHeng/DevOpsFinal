package com.example.demo.service;

import com.example.demo.entity.Terrain;
import com.example.demo.repository.TerrainRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TerrainServiceTest {

    @Mock
    private TerrainRepository terrainRepository;

    @InjectMocks
    private TerrainService terrainService;

    private Terrain terrain;

    @BeforeEach
    void setUp() {
        terrain = Terrain.builder()
                .id(1L)
                .name("Mountain View")
                .location("Highlands")
                .pricePerDay(new BigDecimal("100.00"))
                .type("Mountain")
                .build();
    }

    @Test
    void getAllTerrains_ShouldReturnList() {
        when(terrainRepository.findAll()).thenReturn(Arrays.asList(terrain));

        List<Terrain> result = terrainService.getAllTerrains();

        assertEquals(1, result.size());
        assertEquals("Mountain View", result.get(0).getName());
    }

    @Test
    void getTerrainById_WhenExists_ShouldReturnTerrain() {
        when(terrainRepository.findById(1L)).thenReturn(Optional.of(terrain));

        Optional<Terrain> result = terrainService.getTerrainById(1L);

        assertTrue(result.isPresent());
        assertEquals("Mountain View", result.get().getName());
    }

    @Test
    void createTerrain_ShouldReturnSavedTerrain() {
        when(terrainRepository.save(any(Terrain.class))).thenReturn(terrain);

        Terrain result = terrainService.createTerrain(terrain);

        assertNotNull(result);
        assertEquals("Mountain View", result.getName());
    }

    @Test
    void deleteTerrain_ShouldInvokeRepository() {
        doNothing().when(terrainRepository).deleteById(1L);

        terrainService.deleteTerrain(1L);

        verify(terrainRepository, times(1)).deleteById(1L);
    }
}
