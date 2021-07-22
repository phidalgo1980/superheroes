package es.mindata.superheroes.repository;

import es.mindata.superheroes.model.Superhero;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SuperheroRepository extends JpaRepository<Superhero, Long> {

    List<Superhero> findByNameContaining(String name);
}
