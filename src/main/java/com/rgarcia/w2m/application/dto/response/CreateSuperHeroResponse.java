package com.rgarcia.w2m.application.dto.response;

import com.rgarcia.w2m.common.dto.GeneralResponseDto;
import com.rgarcia.w2m.domain.model.SuperHero;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CreateSuperHeroResponse extends GeneralResponseDto<SuperHero> {
    public CreateSuperHeroResponse(SuperHero superHero) {
        super(superHero);
    }
}
