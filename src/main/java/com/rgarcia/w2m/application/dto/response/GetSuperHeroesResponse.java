package com.rgarcia.w2m.application.dto.response;

import com.rgarcia.w2m.common.dto.GeneralResponseDto;
import com.rgarcia.w2m.domain.model.SuperHero;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class GetSuperHeroesResponse extends GeneralResponseDto<List<SuperHero>> {
    public GetSuperHeroesResponse(List<SuperHero> superHeroes) {
        super(superHeroes);
    }
}
