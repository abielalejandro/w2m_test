package com.rgarcia.w2m.application.dto.response;

import com.rgarcia.w2m.common.dto.GeneralResponseDto;
import com.rgarcia.w2m.domain.model.SuperHero;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GetSuperHeroResponse extends GeneralResponseDto<SuperHero> {
    public GetSuperHeroResponse(SuperHero superHero) {
        super(superHero);
    }
}
