package edu.ctda.cindy.controller.validator.customer;

import java.util.ArrayList;
import java.util.List;

import edu.ctda.cindy.controller.validator.Validator;
import edu.ctda.cindy.crosscutting.helper.MailHelper;
import edu.ctda.cindy.crosscutting.messages.Message;
import edu.ctda.cindy.crosscutting.messages.Messages;
import edu.ctda.cindy.domain.CustomerDTO;

public class CreateCustomerValidator implements Validator<CustomerDTO>{

	@Override
	public List<Message> validate(CustomerDTO dto) {
		List<Message> messages = new ArrayList<>();
		validateEmail(dto.getEmail(), messages);
		validateUsername(dto.getName(), messages);
		validateSurname(dto.getSurname(), messages);
		validateUserPassword(dto.getPassword(), messages);
		validatePhone(dto.getPhone(), messages);
		validateCedula(dto.getCedula(), messages);
		return messages;
	}
	
	private void validateEmail(String email, List<Message> messages) {
		if(MailHelper.isDefaultMail(email)) {
			messages.add(Message.createErrorMessage(Messages.CreateCustomerValidator.MAIL_IS_DEFAULT_ERROR));
		}
		if(!MailHelper.isMailValid(email)) {
			messages.add(Message.createErrorMessage(Messages.CreateCustomerValidator.MAIL_IS_INVALID_FORMAT_ERROR));
		}
	}
	private void validateUsername(String username, List<Message> messages) {
		if (!(username.length() >= 1 && username.length() <= 50)) {
			messages.add(Message.createErrorMessage(Messages.CreateCustomerValidator.NAME_IS_INVALID_ERROR));
		}
	}
	
	private void validateSurname(String surname, List<Message> messages) {
		if (!(surname.length() >= 1 && surname.length() <= 50)) {
			messages.add(Message.createErrorMessage(Messages.CreateCustomerValidator.SURNAME_IS_INVALID_ERROR));
		}
	}
	
	private void validatePhone(String phone, List<Message> messages) {
		if (phone.length() != 10) {
			messages.add(Message.createErrorMessage(Messages.CreateCustomerValidator.PHONE_IS_INVALID_ERROR));
		}
	}
	
	private void validateCedula(String cedula, List<Message> messages) {
		if(!(cedula.length() >= 5 && cedula.length() <= 10)) {
			messages.add(Message.createErrorMessage(Messages.CreateCustomerValidator.CEDULA_IS_INVALID_ERROR));
		}
	}
	
	private void validateUserPassword(String password, List<Message> messages) {
		if (password.length() < 8) {
			messages.add(Message.createErrorMessage(Messages.CreateCustomerValidator.PASSWORD_IS_INVALID_ERROR));
		}
	}

}
