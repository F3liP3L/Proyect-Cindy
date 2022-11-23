package edu.ctda.cindy.controller.validator;

import java.util.List;

import edu.ctda.cindy.crosscutting.messages.Message;

public interface Validator<T> {
	
	List<Message> validate(T dto);

}
