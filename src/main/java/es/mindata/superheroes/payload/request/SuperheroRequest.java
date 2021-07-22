package es.mindata.superheroes.payload.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class SuperheroRequest {

    private String name;

    private String realName;

    private String occupation;

    private String gender;

    private String universe;

}
