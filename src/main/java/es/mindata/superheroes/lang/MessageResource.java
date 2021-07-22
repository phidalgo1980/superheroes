package es.mindata.superheroes.lang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageResource {

	@Autowired
	private MessageSource messageSource;

	public String getMessage(String propKey) {
		return getMessage(propKey, "");
	}

	public String getMessage(String propKey, String... message) {
		return messageSource.getMessage(propKey, message, LocaleContextHolder.getLocale());
	}
}
