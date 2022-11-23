package edu.ctda.cindy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ctda.cindy.controller.response.Response;
import edu.ctda.cindy.crosscutting.exception.CindyCustomException;
import edu.ctda.cindy.crosscutting.messages.Messages;
import edu.ctda.cindy.domain.EventDTO;
import edu.ctda.cindy.service.command.CreateEventCommand;
import edu.ctda.cindy.service.command.DeleteEventCommand;
import edu.ctda.cindy.service.command.FindEventByMonthCommand;
import edu.ctda.cindy.service.command.UpdateEventCommand;
import edu.ctda.cindy.service.command.implementation.CreateEventCommandImpl;
import edu.ctda.cindy.service.command.implementation.DeleteEventCommandImpl;
import edu.ctda.cindy.service.command.implementation.FindEventByMonthCommandImpl;
import edu.ctda.cindy.service.command.implementation.UpdateEventCommandImpl;

@RestController
@RequestMapping("/api/event")
public class EventController {
	
	private CreateEventCommand createEventCommand = new CreateEventCommandImpl();
	private DeleteEventCommand deleteEventCommand = new DeleteEventCommandImpl();
	private FindEventByMonthCommand findEventMonthCommand = new FindEventByMonthCommandImpl();
	private UpdateEventCommand updateEventCommand = new UpdateEventCommandImpl();
	
	@PostMapping()
	public ResponseEntity<Response<EventDTO>> createEvent(@RequestBody EventDTO event) {
		
		final Response<EventDTO> response = new Response<>();
		HttpStatus httpStatus = HttpStatus.OK;

		try {
				createEventCommand.execute(event);
				List<EventDTO> data = new ArrayList<>();
				data.add(event);
				response.setData(data);
				response.addSuccessMessage(Messages.ResponseEventController.EVENT_CREATED_SUCCESSFULLY);
		} catch (final CindyCustomException exception) {
			httpStatus = HttpStatus.BAD_REQUEST;

			if (exception.isTechnicalException()) {
				response.addErrorMessage(Messages.ResponseEventController.EVENT_CREATED_ERROR);
			} else {
				response.addErrorMessage(exception.getMessage());
			}
			exception.printStackTrace();

		} catch (final Exception exception) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			response.addFatalMessage(Messages.ResponseEventController.EVENT_CREATED_UNEXPECTED_ERROR);

			exception.printStackTrace();
		}
		return new ResponseEntity<>(response, httpStatus);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") UUID id) {
		final Response<UUID> response = new Response<>();
		HttpStatus httpStatus = HttpStatus.OK;
		try {
			deleteEventCommand.execute(id);
			List<UUID> data = new ArrayList<>();
			data.add(id);
			response.setData(data);
			response.addSuccessMessage(Messages.ResponseEventController.EVENT_DELETED_SUCCESSFULLY);
	} catch (final CindyCustomException exception) {
		httpStatus = HttpStatus.BAD_REQUEST;

		if (exception.isTechnicalException()) {
			response.addErrorMessage(Messages.ResponseEventController.EVENT_DELETED_ERROR);
		} else {
			response.addErrorMessage(exception.getMessage());
		}
		exception.printStackTrace();

	} catch (final Exception exception) {
		httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		response.addFatalMessage(Messages.ResponseEventController.EVENT_DELETED_UNEXPECTED_ERROR);

		exception.printStackTrace();
	}
		return new ResponseEntity<>(response, httpStatus);
	
	}
	
	@GetMapping("/{month}")
	public ResponseEntity<List<EventDTO>> findEventByMonth (@PathVariable("month") String month){
		return new ResponseEntity<>(findEventMonthCommand.execute(month), HttpStatus.OK);
	}
	
	@PutMapping()
	public ResponseEntity<Response<EventDTO>> updateEvent(@RequestBody EventDTO event) {
		
		final Response<EventDTO> response = new Response<>();
		HttpStatus httpStatus = HttpStatus.OK;

		try {
				updateEventCommand.execute(event);
				List<EventDTO> data = new ArrayList<>();
				data.add(event);
				response.setData(data);
				response.addSuccessMessage(Messages.ResponseEventController.EVENT_UPDATE_SUCCESSFULLY);
		} catch (final CindyCustomException exception) {
			httpStatus = HttpStatus.BAD_REQUEST;

			if (exception.isTechnicalException()) {
				response.addErrorMessage(Messages.ResponseEventController.EVENT_UPDATE_ERROR);
			} else {
				response.addErrorMessage(exception.getMessage());
			}
			exception.printStackTrace();

		} catch (final Exception exception) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			response.addFatalMessage(Messages.ResponseEventController.EVENT_UPDATE_UNEXPECTED_ERROR);

			exception.printStackTrace();
		}
		return new ResponseEntity<>(response, httpStatus);
	}
	

}
