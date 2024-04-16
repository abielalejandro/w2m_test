package com.rgarcia.w2m.infrastructure.dao.repository;

import com.rgarcia.w2m.infrastructure.model.SuperHeroEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuperHeroRepository extends JpaRepository<SuperHeroEntity, Long> {
    List<SuperHeroEntity> findByNameLikeIgnoreCase(String name, Pageable pageable);
    List<SuperHeroEntity> findByNameLikeIgnoreCase(String name);
    Long countByNameLikeIgnoreCase(String name);
}
