package es.mindata.superheroes.rest;



import es.mindata.superheroes.security.service.UserDetailsImpl;
import es.mindata.superheroes.payload.request.LoginRequest;
import es.mindata.superheroes.payload.response.JwtResponse;
import es.mindata.superheroes.security.jwt.JwtUtils;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import java.util.stream.Collectors;


@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
    AuthenticationManager authenticationManager;

	@Autowired
	JwtUtils jwtUtils;
	

	@PostMapping("/signin")
	@ApiOperation(value = "Sign user in", response = JwtResponse.class)
	public ResponseEntity<?> authenticateUser(@ApiParam(value = "User Credentials") @Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(new JwtResponse(jwt,
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 roles));
	}

}
