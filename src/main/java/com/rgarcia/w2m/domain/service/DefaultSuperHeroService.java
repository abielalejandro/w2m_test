package com.rgarcia.w2m.domain.service;

import com.rgarcia.w2m.common.dto.PaginationDto;
import com.rgarcia.w2m.common.dto.SearchSuperHeroParameters;
import com.rgarcia.w2m.domain.dao.SuperHeroDao;
import com.rgarcia.w2m.domain.model.SuperHero;
import com.rgarcia.w2m.exceptions.SuperHeroNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class DefaultSuperHeroService implements SuperHeroService{

    SuperHeroDao dao;

    @Override
    public SuperHero get(Long id) {
        return dao
                .get(id)
                .orElseThrow(SuperHeroNotFoundException::new);
    }


    @Override
    public SuperHero create(SuperHero hero) {
        return dao.createOrUpdate(hero);
    }


    @Override
    public SuperHero update(Long id, SuperHero hero) {
        SuperHero persisted = get(id);
        persisted.setName(hero.getName());
        persisted.setPower(hero.getPower());
        persisted.setRealName(hero.getRealName());
        return dao.createOrUpdate(persisted);
    }


    @Override
    public void delete(Long id) {
        get(id);
        dao.delete(id);
    }


    @Override
    public PaginationDto<SuperHero> search(SearchSuperHeroParameters parameters) {
        List<SuperHero> superHeroes= dao.search(parameters);
        Long total= dao.count(parameters.getQuery());
        PaginationDto<SuperHero> paginationDto= new PaginationDto<SuperHero>(
                superHeroes,
                total,
                parameters.getPage(),
                parameters.getLimit()
        );

        return paginationDto;
    }

    @Override
    public List<SuperHero> searchAll(String query) {
        return dao.searchAll(query);
    }
}
