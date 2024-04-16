package com.rgarcia.w2m.domain.service;

import com.rgarcia.w2m.common.dto.PaginationDto;
import com.rgarcia.w2m.common.dto.SearchSuperHeroParameters;
import com.rgarcia.w2m.domain.model.SuperHero;

import java.util.List;

public interface SuperHeroService {

    SuperHero get(Long id);
    SuperHero create(SuperHero hero);
    SuperHero update(Long id, SuperHero hero);
    void delete(Long id);
    PaginationDto<SuperHero> search(SearchSuperHeroParameters parameters);

    List<SuperHero> searchAll(String query);

}
