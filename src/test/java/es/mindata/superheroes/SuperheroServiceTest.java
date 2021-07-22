package es.mindata.superheroes;

import es.mindata.superheroes.model.Superhero;
import es.mindata.superheroes.payload.request.SuperheroRequest;
import es.mindata.superheroes.repository.SuperheroRepository;
import es.mindata.superheroes.service.SuperheroService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SuperheroServiceTest {

    @Mock
    private SuperheroRepository superheroRepository;

    @InjectMocks
    private SuperheroService superheroService;

    @Test
    void shouldUpdateSuperheroSuccesfully(){
        Superhero superheroEntity = buildSuperheroEntity();
        SuperheroRequest superheroRequest = buildSuperheroRequest();

        when(superheroRepository.save(superheroEntity)).then(AdditionalAnswers.returnsFirstArg());

        Superhero superheroSaved = superheroService.update(superheroEntity, superheroRequest);

        assertThat(superheroSaved.getUniverse()).isEqualTo("Marvel");
    }

    @Test
    void shouldPatchSuperheroSuccesfully(){
        Superhero superheroEntity = buildSuperheroEntity();
        SuperheroRequest superheroRequest = buildSuperheroRequest();
        superheroRequest.setGender(null);

        when(superheroRepository.save(superheroEntity)).then(AdditionalAnswers.returnsFirstArg());

        Superhero superheroSaved = superheroService.patch(superheroEntity, superheroRequest);

        assertThat(superheroSaved.getGender()).isEqualTo("Male");
    }

    @Test
    void shouldReturnsAPagedListOfSuperheros() {
        Pageable pageRequest = PageRequest.of(0, 4);

        when(superheroRepository.findAll(pageRequest)).thenReturn(buildSuperheroList());

        List<Superhero> superheroes = superheroService.findAll(pageRequest).getContent();

        assertThat(superheroes.size()).isEqualTo(4);
    }

    @Test
    void shouldReturnsAPagedListOfSuperherosMatchingName() {
        Pageable pageRequest = PageRequest.of(0, 4);

        when(superheroRepository.findByNameContaining("man")).thenReturn(buildSuperheroList().getContent());

        List<Superhero> superheroes = superheroService.findByName("man");

        assertThat(superheroes.size()).isEqualTo(4);
    }

    @Test
    void shouldReturnsASuperheroById() {
        when(superheroRepository.findById(1L)).thenReturn(Optional.of(buildSuperheroEntity()));

        Superhero superheroe = superheroService.findById(1L).get();

        assertThat(superheroe.getId()).isEqualTo(1L);
    }


    public Superhero buildSuperheroEntity(){
        return Superhero.builder().id(1L).name("Superman").realName("Clark Kent").occupation("Journalist").gender("Male").universe("DC Comics").build();
    }

    public SuperheroRequest buildSuperheroRequest(){
        return SuperheroRequest.builder().name("Superman").realName("Clark Kent").occupation("Journalist").gender("Male").universe("Marvel").build();
    }

    public Page<Superhero> buildSuperheroList(){
        return new PageImpl(Arrays.asList(
                Superhero.builder().id(1L).name("Superman").realName("Clark Kent").occupation("Journalist").gender("Male").universe("DC Comics").build(),
                Superhero.builder().id(2L).name("Batman").realName("Bruce Wayne").occupation("Businessman").gender("Male").universe("DC Comics").build(),
                Superhero.builder().id(3L).name("Wonder Woman").realName("Diana Prince").occupation("Secretary").gender("Female").universe("DC Comics").build(),
                Superhero.builder().id(4L).name("Spiderman").realName("Peter Parker").occupation("'Photographer'").gender("Male").universe("Marvel").build()
        ));
    }
}
