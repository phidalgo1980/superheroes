package es.mindata.superheroes.mapper;

import es.mindata.superheroes.model.Superhero;
import es.mindata.superheroes.payload.response.SuperheroResponse;

public class SuperheroMapper {

    public static SuperheroResponse entityToResponse(Superhero superhero) {
        return SuperheroResponse.builder()
                .name(superhero.getName())
                .realName(superhero.getRealName())
                .occupation(superhero.getOccupation())
                .universe(superhero.getUniverse())
                .gender(superhero.getGender())
                .build();
    }
}
