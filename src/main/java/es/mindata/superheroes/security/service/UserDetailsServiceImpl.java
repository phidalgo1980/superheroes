package es.mindata.superheroes.security.service;


import es.mindata.superheroes.model.User;
import es.mindata.superheroes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserRepository userRepository;

	@Autowired
	public UserDetailsServiceImpl(UserRepository usuarioRepository) {
		this.userRepository = usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User: " + username + " not found"));
		
		return UserDetailsImpl.build(user);
	}
}
