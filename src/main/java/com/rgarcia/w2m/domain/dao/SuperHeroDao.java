package com.rgarcia.w2m.domain.dao;

import com.rgarcia.w2m.common.dto.SearchSuperHeroParameters;
import com.rgarcia.w2m.domain.model.SuperHero;

import java.util.List;
import java.util.Optional;

public interface SuperHeroDao {

    Optional<SuperHero> get(Long id);
    SuperHero createOrUpdate(SuperHero hero);
    void delete(Long id);
    List<SuperHero> search(SearchSuperHeroParameters parameters);
    Long count(String query);
    List<SuperHero> searchAll(String query);
}
