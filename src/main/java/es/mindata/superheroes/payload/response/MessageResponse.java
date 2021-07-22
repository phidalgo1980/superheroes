package es.mindata.superheroes.payload.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class MessageResponse {
	
	private String message;

}
