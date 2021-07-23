package es.mindata.superheroes;


import org.json.JSONObject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SuperheroControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	static String jwtToken;
	
    static String jsonTemplate = "{\"username\":\"%s\", \"password\":\"%s\"}";

	
	@BeforeEach
	public void setup() throws Exception {

	    String username = "admin";
	    String password = "supersecret";
	    String body = String.format(jsonTemplate, username, password);

	    MvcResult result = this.mockMvc.perform(post("/auth/signin")
						.contentType(MediaType.APPLICATION_JSON)
						.content(body))
						.andDo(print())
						.andExpect(status().isOk()).andReturn();
	    
	    String response = result.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(response);
        jwtToken = obj.getString("token");
	}
	
	
	@Test
	public void shouldReturnSuperhero() throws Exception {

	    mockMvc.perform(get("/superhero/1")
				.header("Authorization", "Bearer " + jwtToken))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Superman"))
				.andReturn();
	}

	@Test
	public void shouldReturn404NotFoundSuperhero() throws Exception {

		mockMvc.perform(get("/superhero/10")
				.header("Authorization", "Bearer " + jwtToken))
				.andDo(print())
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.message").value("El superheroe no se ha encontrado"))
				.andReturn();
	}
	
}
