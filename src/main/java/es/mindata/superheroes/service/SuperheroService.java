package es.mindata.superheroes.service;

import es.mindata.superheroes.model.Superhero;
import es.mindata.superheroes.payload.request.SuperheroRequest;
import es.mindata.superheroes.repository.SuperheroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class SuperheroService {

    @Autowired
    SuperheroRepository superheroRepository;


    public Optional<Superhero> findById(Long id) {
        return superheroRepository.findById(id);
    }

    @Cacheable(value="superheroes")
    public List<Superhero> findByName(String name) {
        return superheroRepository.findByNameContaining(name);
    }

    public Page<Superhero> findAll(Pageable paging) {
        return superheroRepository.findAll(paging);
    }

    @CacheEvict(value = "superheroes", allEntries = true)
    public Superhero update(Superhero superhero, SuperheroRequest superheroRequest) {

        superhero.setName(superheroRequest.getName());
        superhero.setRealName(superheroRequest.getRealName());
        superhero.setGender(superheroRequest.getGender());
        superhero.setOccupation(superheroRequest.getOccupation());
        superhero.setUniverse(superheroRequest.getUniverse());

        return superheroRepository.save(superhero);
    }

    @CacheEvict(value = "superheroes", allEntries = true)
    public Superhero patch(Superhero superhero, SuperheroRequest superheroRequest) {

        if(superheroRequest.getName() != null)
            superhero.setName(superheroRequest.getName());
        if(superheroRequest.getRealName() != null)
            superhero.setRealName(superheroRequest.getRealName());
        if(superheroRequest.getGender() != null)
            superhero.setGender(superheroRequest.getGender());
        if(superheroRequest.getOccupation() != null)
            superhero.setOccupation(superheroRequest.getOccupation());
        if(superheroRequest.getUniverse() != null)
            superhero.setUniverse(superheroRequest.getUniverse());

        return superheroRepository.save(superhero);
    }

    @CacheEvict(value = "superheroes", allEntries = true)
    public void delete(Superhero superhero) {
        superheroRepository.delete(superhero);
    }
}
