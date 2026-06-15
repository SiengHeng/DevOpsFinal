package com.example.demo.controller;

import com.example.demo.entity.TerrainImage;
import com.example.demo.service.TerrainImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/terrain-images")
@RequiredArgsConstructor
public class TerrainImageController {
    private final TerrainImageService terrainImageService;

    @GetMapping
    public List<TerrainImage> getAllImages() {
        return terrainImageService.getAllImages();
    }

    @GetMapping("/terrain/{terrainId}")
    public List<TerrainImage> getImagesByTerrain(@PathVariable Long terrainId) {
        return terrainImageService.getImagesByTerrainId(terrainId);
    }

    @PostMapping
    public TerrainImage createImage(@RequestBody TerrainImage image) {
        return terrainImageService.createImage(image);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        terrainImageService.deleteImage(id);
        return ResponseEntity.noContent().build();
    }
}
