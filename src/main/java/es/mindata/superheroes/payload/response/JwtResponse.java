package es.mindata.superheroes.payload.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@SuperBuilder
public class JwtResponse {
	
	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private List<String> roles;

	public JwtResponse(String accessToken, Long id, String username, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.roles = roles;
	}
}