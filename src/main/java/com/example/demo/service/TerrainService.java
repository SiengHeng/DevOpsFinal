package com.example.demo.service;

import com.example.demo.entity.Terrain;
import com.example.demo.repository.TerrainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TerrainService {
    private final TerrainRepository terrainRepository;

    public List<Terrain> getAllTerrains() {
        return terrainRepository.findAll();
    }

    public Optional<Terrain> getTerrainById(Long id) {
        return terrainRepository.findById(id);
    }

    public Terrain createTerrain(Terrain terrain) {
        return terrainRepository.save(terrain);
    }

    public Terrain updateTerrain(Long id, Terrain terrainDetails) {
        Terrain terrain = terrainRepository.findById(id).orElseThrow(() -> new RuntimeException("Terrain not found"));
        terrain.setName(terrainDetails.getName());
        terrain.setDescription(terrainDetails.getDescription());
        terrain.setPricePerDay(terrainDetails.getPricePerDay());
        terrain.setLocation(terrainDetails.getLocation());
        terrain.setType(terrainDetails.getType());
        return terrainRepository.save(terrain);
    }

    public void deleteTerrain(Long id) {
        terrainRepository.deleteById(id);
    }

    public List<Terrain> searchByLocation(String location) {
        return terrainRepository.findByLocationContainingIgnoreCase(location);
    }
}
