package com.rgarcia.w2m.domain.service;

import com.rgarcia.w2m.common.dto.PaginationDto;
import com.rgarcia.w2m.common.dto.SearchSuperHeroParameters;
import com.rgarcia.w2m.domain.dao.SuperHeroDao;
import com.rgarcia.w2m.domain.model.SuperHero;
import com.rgarcia.w2m.exceptions.SuperHeroNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DefaultSuperHeroServiceTest {

    @Mock
    SuperHeroDao dao;

    @InjectMocks
    private DefaultSuperHeroService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenGetHeroById() {
        Long id =1l;
        Optional<SuperHero> hero = Optional.of(new SuperHero(
            1l,"Superman","Clark Kent","Super fuerza"
        ));

        when(dao.get(id)).thenReturn(hero);
        SuperHero result= service.get(id);
        assertEquals(result.getId(),id);
        assertEquals(result.getName(),"Superman");

    }

    @Test
    void whenGetHeroByIdThrowNotFound() {
        Long id =1l;
        Optional<SuperHero> hero = Optional.ofNullable(null);

        when(dao.get(id)).thenReturn(hero);
        SuperHeroNotFoundException thrown = assertThrows(
                SuperHeroNotFoundException.class,
                () -> service.get(id),
                "Superhero not found"
        );
        assertTrue(thrown.getMessage().contains("Superhero not found"));
    }

    @Test
    void whenCreateHero() {
        SuperHero hero = new SuperHero(
                1l,"Superman","Clark Kent","Super fuerza"
        );

        when(dao.createOrUpdate(hero)).thenReturn(hero);
        SuperHero result= service.create(hero);
        assertEquals(result.getId(),hero.getId());
        assertEquals(result.getName(),hero.getName());

    }

    @Test
    void whenUpdateHero() {
        Long id = 1l;
        SuperHero hero = new SuperHero(
                1l,"Superman","Clark Kent","Super fuerza"
        );

        Optional<SuperHero> heroPersisted = Optional.of(new SuperHero(
                1l,"Superman X","Clark Kent","Super fuerza"
        ));

        when(dao.get(id)).thenReturn(heroPersisted);
        when(dao.createOrUpdate(hero)).thenReturn(hero);
        SuperHero result= service.update(id,hero);
        assertEquals(result.getId(),hero.getId());
        assertEquals(result.getName(),hero.getName());

    }

    @Test
    void whenDeleteHero() {
        Long id = 1l;
        Optional<SuperHero> heroPersisted = Optional.of(new SuperHero(
                1l,"Superman X","Clark Kent","Super fuerza"
        ));

        when(dao.get(id)).thenReturn(heroPersisted);
        service.delete(id);
        assertTrue(true);

    }

    @Test
    void whenSearch() {
        List<SuperHero> heroes = List.of(
            new SuperHero(
                1l,"Superman","Clark Kent","Super fuerza"
            ),
            new SuperHero(
                    1l,"Batman","Bruce","Millonario"
            )
        );

        SearchSuperHeroParameters parameters = new SearchSuperHeroParameters();
        parameters.setPage(1);
        parameters.setLimit(2);
        parameters.setQuery("");
        when(dao.search(parameters)).thenReturn(heroes);
        when(dao.count(parameters.getQuery())).thenReturn(10l);

        PaginationDto<SuperHero> result = service.search(parameters);

        assertEquals(result.getData().size(),2);
        assertEquals(result.getTotal(),10);
        assertEquals(result.getPages(),5);
    }

    @Test
    void whenSearchWithFilterByName() {
        List<SuperHero> heroes = List.of(
                new SuperHero(
                        1l,"Batman","Bruce","Millonario"
                )
        );

        SearchSuperHeroParameters parameters = new SearchSuperHeroParameters();
        parameters.setPage(1);
        parameters.setLimit(2);
        parameters.setQuery("Batman");
        when(dao.search(parameters)).thenReturn(heroes);
        when(dao.count(parameters.getQuery())).thenReturn(10l);

        PaginationDto<SuperHero> result = service.search(parameters);

        assertEquals(result.getData().size(),1);
        assertEquals(result.getTotal(),10);
        assertEquals(result.getPages(),5);
    }

}