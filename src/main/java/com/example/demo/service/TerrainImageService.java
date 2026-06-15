package com.example.demo.service;

import com.example.demo.entity.TerrainImage;
import com.example.demo.repository.TerrainImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TerrainImageService {
    private final TerrainImageRepository terrainImageRepository;

    public List<TerrainImage> getAllImages() {
        return terrainImageRepository.findAll();
    }

    public List<TerrainImage> getImagesByTerrainId(Long terrainId) {
        return terrainImageRepository.findByTerrainId(terrainId);
    }

    public TerrainImage createImage(TerrainImage image) {
        return terrainImageRepository.save(image);
    }

    public void deleteImage(Long id) {
        terrainImageRepository.deleteById(id);
    }
}
