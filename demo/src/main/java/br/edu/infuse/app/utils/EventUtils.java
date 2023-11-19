package br.edu.infuse.app.utils;

import java.time.LocalDateTime;

import br.edu.infuse.app.exception.BadRequestException;
import br.edu.infuse.app.model.Message;
import br.edu.infuse.app.model.Order;
import br.edu.infuse.app.repository.OrderRepository;

public class EventUtils {	
	public static final String SAVE = "save";
	public static final String LIST = "list";
	
	public static Message saveException(Exception e) {
		return Message.builder()
				.msg(e.getMessage())
				.errorDate(LocalDateTime.now())
				.build();
	}
}