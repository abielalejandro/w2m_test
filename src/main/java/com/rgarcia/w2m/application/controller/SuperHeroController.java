package com.rgarcia.w2m.application.controller;


import com.rgarcia.w2m.application.dto.request.CreateSuperHero;
import com.rgarcia.w2m.application.dto.response.GetSuperHeroesPaginationResponse;
import com.rgarcia.w2m.common.Timed;
import com.rgarcia.w2m.common.dto.GeneralResponseDto;
import com.rgarcia.w2m.application.dto.response.GetSuperHeroResponse;
import com.rgarcia.w2m.application.dto.response.GetSuperHeroesResponse;
import com.rgarcia.w2m.application.mapper.SuperHeroAppMapper;
import com.rgarcia.w2m.common.dto.SearchSuperHeroParameters;
import com.rgarcia.w2m.domain.service.SuperHeroService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/superheroes")
public class SuperHeroController {

    SuperHeroService service;
    SuperHeroAppMapper mapper;

    @Timed
    @Cacheable("superheroes")
    @GetMapping()
    @PreAuthorize("hasRole('ROLE_USER')")
    public GetSuperHeroesPaginationResponse search(
            @RequestParam(defaultValue = "1", name="page") @Positive Integer page,
            @RequestParam(defaultValue = "10", name="size") @Positive Integer size,
            @RequestParam(name="q") Optional<String> q
    ) {
        SearchSuperHeroParameters parameters = new SearchSuperHeroParameters();
        parameters.setQuery(q.orElse(""));
        parameters.setPage(page);
        parameters.setLimit(size);
        return mapper.toResponse(service.search(parameters));
    }

    @Timed
    @GetMapping(path = "/all")
    @PreAuthorize("hasRole('ROLE_USER')")
    public GetSuperHeroesResponse searchAll(
            @RequestParam(name="q") Optional<String> q
    ) {
        return mapper.toResponse(service.searchAll(q.orElse("")));
    }

    @Timed
    @GetMapping(path = "/{id}")
    @PreAuthorize("hasRole('ROLE_USER')") 
    public GetSuperHeroResponse searchById(@PathVariable Long id) {
        return mapper.toResponse(service.get(id));
    }

    @Timed
    @CacheEvict(value="superheroes", allEntries=true)
    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')") 
    public GetSuperHeroResponse create (@RequestBody @Valid @NotNull CreateSuperHero hero) {
        return mapper.toResponse(service.create(mapper.toDomain(hero)));
    }

    @Timed
    @CacheEvict(value="superheroes", allEntries=true)
    @RequestMapping(path = "/{id}",method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ROLE_ADMIN')") 
    public GetSuperHeroResponse update (
            @PathVariable @NotNull @Positive Long id,
            @RequestBody @Valid @NotNull CreateSuperHero hero) {
        return mapper.toResponse(service.update(id,mapper.toDomain(hero)));
    }

    @Timed
    @CacheEvict(value="superheroes", allEntries=true)
    @RequestMapping(path = "/{id}",method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ADMIN')") 
    public GeneralResponseDto<String> delete (@PathVariable @NotNull @Positive Long id) {
        service.delete(id);
        return new GeneralResponseDto<String>("OK");
    }

}
