package es.mindata.superheroes.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(	name = "superheroes" )
public class Superhero {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="real_name")
    private String realName;

    @Column(name="occupation")
    private String occupation;

    @Column(name="gender")
    private String gender;

    @Column(name="universe")
    private String universe;
}
