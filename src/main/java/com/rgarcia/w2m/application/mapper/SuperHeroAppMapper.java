package com.rgarcia.w2m.application.mapper;

import com.rgarcia.w2m.application.dto.request.CreateSuperHero;
import com.rgarcia.w2m.application.dto.response.GetSuperHeroResponse;
import com.rgarcia.w2m.application.dto.response.GetSuperHeroesPaginationResponse;
import com.rgarcia.w2m.application.dto.response.GetSuperHeroesResponse;
import com.rgarcia.w2m.common.dto.PaginationDto;
import com.rgarcia.w2m.domain.model.SuperHero;
import com.rgarcia.w2m.utils.ConvertDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SuperHeroAppMapper {

    private final ConvertDto convert = ConvertDto.getInstance();

    public GetSuperHeroResponse toResponse(SuperHero superHero) {
        return new GetSuperHeroResponse(superHero);
    }

    public GetSuperHeroesResponse toResponse(List<SuperHero> superHeroes) {
        return new GetSuperHeroesResponse(superHeroes);
    }

    public GetSuperHeroesPaginationResponse toResponse(PaginationDto<SuperHero> paginationDto) {
        return new GetSuperHeroesPaginationResponse(paginationDto);
    }

    public SuperHero toDomain(CreateSuperHero dto) {
        return (SuperHero) convert.create(dto, SuperHero.class);
    }
}
