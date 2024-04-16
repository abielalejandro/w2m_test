package com.rgarcia.w2m.infrastructure.dao;

import com.rgarcia.w2m.common.dto.SearchSuperHeroParameters;
import com.rgarcia.w2m.domain.dao.SuperHeroDao;
import com.rgarcia.w2m.domain.model.SuperHero;
import com.rgarcia.w2m.infrastructure.dao.repository.SuperHeroRepository;
import com.rgarcia.w2m.infrastructure.mapper.SuperHeroMapper;
import com.rgarcia.w2m.infrastructure.model.SuperHeroEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Repository
public class DefaultSuperHeroDao implements SuperHeroDao {

    SuperHeroMapper mapper;
    SuperHeroRepository repository;

    @Override
    public Optional<SuperHero> get(Long id) {
        return
                repository
                        .findById(id)
                        .map(entity-> {
                            return mapper.toDomain(entity);
                        });
    }

    @Override
    public SuperHero createOrUpdate(SuperHero hero) {
        SuperHeroEntity entity = mapper.toEntity(hero);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<SuperHero> search(SearchSuperHeroParameters parameters) {
        if (null==parameters.getQuery()) {
            parameters.setQuery("");
        }

        parameters.setQuery("%"+parameters.getQuery()+"%");

        return repository
                .findByNameLikeIgnoreCase(
                        parameters.getQuery(),
                        PageRequest.of(parameters.getPage()-1, parameters.getLimit()))
                .stream()
                .map((entity)->{
                    return mapper.toDomain(entity);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Long count(String query) {
        if (null==query) {
            query = "";
        }
        query = "%"+query+"%";
        return repository.countByNameLikeIgnoreCase(query);
    }

    @Override
    public List<SuperHero> searchAll(String query) {
        if (null==query) {
            query = "";
        }
        query = "%"+query+"%";
        return repository
                .findByNameLikeIgnoreCase(query)
                .stream()
                .map((entity)->{
                    return mapper.toDomain(entity);
                })
                .collect(Collectors.toList());
    }
}
