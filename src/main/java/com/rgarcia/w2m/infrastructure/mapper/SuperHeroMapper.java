package com.rgarcia.w2m.infrastructure.mapper;

import com.rgarcia.w2m.domain.model.SuperHero;
import com.rgarcia.w2m.infrastructure.model.SuperHeroEntity;
import com.rgarcia.w2m.utils.ConvertDto;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class SuperHeroMapper {
    private final ConvertDto convert = ConvertDto.getInstance();
    public SuperHero toDomain(SuperHeroEntity entity) {
        return (SuperHero) convert.create(entity, SuperHero.class);
    }

    /*public SuperHeroEntity toEntity(SuperHero domain) {
        SuperHeroEntity entity = new SuperHeroEntity();
        entity.setName(domain.getName());
        entity.setPower(domain.getPower());
        entity.setRealName(domain.getRealName());
        return entity;
    }*/

    public SuperHeroEntity toEntity(SuperHero domain) {
        return (SuperHeroEntity) convert.create(domain, SuperHeroEntity.class);
    }
}
