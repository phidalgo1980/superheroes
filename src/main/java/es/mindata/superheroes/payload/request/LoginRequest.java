package es.mindata.superheroes.payload.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@SuperBuilder
public class LoginRequest {
	
	@NotNull
	private String username;

	@NotNull
	private String password;

}