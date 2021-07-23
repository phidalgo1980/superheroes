package es.mindata.superheroes.exception;


import es.mindata.superheroes.payload.response.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class SuperheroControllerAdvise extends ResponseEntityExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(SuperheroControllerAdvise.class);


	@ExceptionHandler({ResourceNotFoundException.class})
	public ResponseEntity<?> handlelNotFoundException(ResourceNotFoundException e){
		return error(HttpStatus.NOT_FOUND, e, e.getMessage());
	}

	@ExceptionHandler({AccessDeniedException.class})
	public ResponseEntity<?> handlelAccessDeniedException(AccessDeniedException e){
		return error(HttpStatus.FORBIDDEN, e, e.getMessage());
	}

	@ExceptionHandler({RuntimeException.class})
	public ResponseEntity<?> handlelRuntimeException(RuntimeException e){
		return error(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage());
	}

	@ExceptionHandler({Exception.class})
	public ResponseEntity<?> handlelException(Exception e){
		return error(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage());
	}
	
	private ResponseEntity<?> error(HttpStatus status, Exception e, String message){
		logger.error("Exception: ", e);
		return ResponseEntity.status(status).body(MessageResponse.builder().message(message).build());
	}
}
