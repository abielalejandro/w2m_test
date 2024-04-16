package com.rgarcia.w2m.application.dto.response;

import com.rgarcia.w2m.common.dto.GeneralResponseDto;
import com.rgarcia.w2m.common.dto.PaginationDto;
import com.rgarcia.w2m.domain.model.SuperHero;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class GetSuperHeroesPaginationResponse extends GeneralResponseDto<PaginationDto<SuperHero>> {
    public GetSuperHeroesPaginationResponse(PaginationDto<SuperHero> superHeroes) {
        super(superHeroes);
    }
}
