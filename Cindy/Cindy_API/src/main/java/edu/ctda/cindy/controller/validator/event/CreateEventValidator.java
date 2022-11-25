package edu.ctda.cindy.controller.validator.event;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import edu.ctda.cindy.controller.validator.Validator;
import edu.ctda.cindy.crosscutting.messages.Message;
import edu.ctda.cindy.crosscutting.messages.Messages;
import edu.ctda.cindy.domain.EventDTO;
import static edu.ctda.cindy.crosscutting.helper.DateHelper.getDateALocalDate;
import static edu.ctda.cindy.crosscutting.helper.DateHelper.isBeforeToday;
import static edu.ctda.cindy.crosscutting.helper.DateHelper.isAfterOrEqual;

public class CreateEventValidator implements Validator<EventDTO> {

	@Override
	public List<Message> validate(EventDTO dto) {
		List<Message> messages = new ArrayList<>();
		validateName(dto.getName(), messages);
		validateDate(dto.getReservationDate(), messages);
		validateDate(dto.getDeliveryDate(), messages);
		validateDateDelivery(dto.getDeliveryDate(),dto.getReservationDate(),messages);
		return messages;
	}
	
	private void validateName(String name, List<Message> messages) {
		if (!(name.length() >= 1 && name.length() <= 50)) {
			messages.add(Message.createErrorMessage(Messages.CreateEventValidator.NAME_IS_INVALID_ERROR));
		}
	}
	
	private void validateDate(Date date, List<Message> messages) {
		if(isBeforeToday(getDateALocalDate(date))) {
			messages.add(Message.createErrorMessage(Messages.CreateEventValidator.DATE_IS_INVALID_ERROR));
		}
	}
	private void validateDateDelivery(Date initDate, Date endDate, List<Message> messages) {
		if(!isAfterOrEqual(getDateALocalDate(initDate), getDateALocalDate(endDate))) {
			messages.add(Message.createErrorMessage(Messages.CreateEventValidator.DATE_DELIVERY_IS_INVALID_ERROR));
		}
	}

}
