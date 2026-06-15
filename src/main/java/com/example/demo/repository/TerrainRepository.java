package com.example.demo.repository;

import com.example.demo.entity.Terrain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TerrainRepository extends JpaRepository<Terrain, Long> {
    List<Terrain> findByLocationContainingIgnoreCase(String location);
    List<Terrain> findByType(String type);
}
