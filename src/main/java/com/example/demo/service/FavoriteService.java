package com.example.demo.service;

import com.example.demo.entity.Favorite;
import com.example.demo.repository.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;

    public List<Favorite> getFavoritesByUserId(Long userId) {
        return favoriteRepository.findByUserId(userId);
    }

    public Favorite addFavorite(Favorite favorite) {
        if (favoriteRepository.existsByUserIdAndTerrainId(favorite.getUser().getId(), favorite.getTerrain().getId())) {
            throw new RuntimeException("Terrain already in favorites");
        }
        return favoriteRepository.save(favorite);
    }

    public void removeFavorite(Long id) {
        favoriteRepository.deleteById(id);
    }
}
