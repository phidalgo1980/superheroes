package es.mindata.superheroes.rest;

import es.mindata.superheroes.annotation.HandlingTime;
import es.mindata.superheroes.exception.ResourceNotFoundException;
import es.mindata.superheroes.lang.MessageResource;
import es.mindata.superheroes.mapper.SuperheroMapper;
import es.mindata.superheroes.model.Superhero;
import es.mindata.superheroes.payload.request.SuperheroRequest;
import es.mindata.superheroes.payload.response.MessageResponse;
import es.mindata.superheroes.payload.response.SuperheroResponse;
import es.mindata.superheroes.service.SuperheroService;
import es.mindata.superheroes.utils.MessageKey;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/superhero")
public class SuperheroController {


    @Autowired
    SuperheroService superheroService;

    @Autowired
    MessageResource messageResource;


    @HandlingTime
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @ApiOperation(value = "Devuelve los datos del superheroe especificado", response = SuperheroResponse.class)
    public ResponseEntity<SuperheroResponse> getSuperheroById(@PathVariable("id") Long id) {

        Superhero superhero = superheroService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(messageResource.getMessage(MessageKey.ERROR_SUPERHERO_NOT_FOUND)));

        return ResponseEntity.ok(SuperheroMapper.entityToResponse(superhero));
    }


    @HandlingTime
    @GetMapping("/name/{name}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @ApiOperation(value = "Devuelve los datos del superheroe por nombre", response = SuperheroResponse.class)
    public ResponseEntity<List<SuperheroResponse>> getSuperheroByName(@PathVariable("name") String name) {

        List<Superhero> superheroes = superheroService.findByName(name);

        if(superheroes.size() == 0)
            throw new ResourceNotFoundException(messageResource.getMessage(MessageKey.ERROR_SUPERHERO_EMPTY));

        return ResponseEntity.ok(fromPageToList(superheroes));
    }


    @HandlingTime
    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @ApiOperation(value = "Devuelve los datos de todos los superheroe")
    public ResponseEntity<List<SuperheroResponse>> getSuperheros(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {

        Page<Superhero> pageSuperheros = superheroService.findAll(PageRequest.of(page, size));

        if(pageSuperheros.getTotalElements() == 0)
            throw new ResourceNotFoundException(messageResource.getMessage(MessageKey.ERROR_SUPERHERO_EMPTY));

        return ResponseEntity.ok(fromPageToList(pageSuperheros.getContent()));
    }


    @HandlingTime
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "Modifica el superheroe definido por id")
    public ResponseEntity<MessageResponse> updateSuperhero(@PathVariable("id") Long id, @RequestBody SuperheroRequest superheroRequest) {

        Superhero superhero = superheroService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(messageResource.getMessage(MessageKey.ERROR_SUPERHERO_NOT_FOUND)));

        superheroService.update(superhero, superheroRequest);

        return ResponseEntity.ok(MessageResponse.builder()
                                    .message(messageResource.getMessage(MessageKey.SUCCESS_SUPERHERO_UPDATED))
                                    .build());
    }


    @HandlingTime
    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "Modifica el superheroe definido por id de forma parcial")
    public ResponseEntity<MessageResponse> patchSuperhero(@PathVariable("id") Long id, @RequestBody SuperheroRequest superheroRequest) {

        Superhero superhero = superheroService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(messageResource.getMessage(MessageKey.ERROR_SUPERHERO_NOT_FOUND)));

        superheroService.patch(superhero, superheroRequest);

        return ResponseEntity.ok(MessageResponse.builder()
                .message(messageResource.getMessage(MessageKey.SUCCESS_SUPERHERO_UPDATED))
                .build());
    }


    @HandlingTime
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "Elimina el superheroe definido por id")
    public ResponseEntity<MessageResponse> deleteSuperhero(@PathVariable("id") Long id) {

        Superhero superhero = superheroService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(messageResource.getMessage(MessageKey.ERROR_SUPERHERO_NOT_FOUND)));

        superheroService.delete(superhero);

        return ResponseEntity.ok(MessageResponse.builder()
                .message(messageResource.getMessage(MessageKey.SUCCESS_SUPERHERO_DELETED))
                .build());
    }

    private List<SuperheroResponse> fromPageToList(List<Superhero> superheroes){

        return superheroes.stream()
                .map( x -> SuperheroMapper.entityToResponse(x))
                .collect(Collectors.toList());
    }
}
