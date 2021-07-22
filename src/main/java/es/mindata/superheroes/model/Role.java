package es.mindata.superheroes.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(	name = "roles" )
public class Role {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;
}
