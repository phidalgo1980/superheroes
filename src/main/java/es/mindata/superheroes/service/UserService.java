package es.mindata.superheroes.service;


import es.mindata.superheroes.model.User;
import es.mindata.superheroes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	

	public Optional<User> findUserByName(String name) {
		return userRepository.findByUsername(name);
	}

}
