package edu.ctda.cindy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ctda.cindy.controller.response.Response;
import edu.ctda.cindy.crosscutting.exception.CindyCustomException;
import edu.ctda.cindy.crosscutting.messages.Messages;
import edu.ctda.cindy.domain.CustomerDTO;
import edu.ctda.cindy.domain.SalonDTO;
import edu.ctda.cindy.service.command.CreateCustomerCommand;
import edu.ctda.cindy.service.command.FindSalonCommand;
import edu.ctda.cindy.service.command.implementation.CreateCustomerCommandImpl;
import edu.ctda.cindy.service.command.implementation.FindSalonCommandImpl;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	private FindSalonCommand findSalonCommand = new FindSalonCommandImpl();
	private CreateCustomerCommand createCustomerCommand = new CreateCustomerCommandImpl();
	
	@GetMapping("/salon")
	public ResponseEntity<List<SalonDTO>> findSalon(){
		return new ResponseEntity<>(findSalonCommand.execute(), HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Response<CustomerDTO>> createUser(@RequestBody CustomerDTO customer) {

		final Response<CustomerDTO> response = new Response<>();
		HttpStatus httpStatus = HttpStatus.OK;

		try {
				createCustomerCommand.execute(customer);
				List<CustomerDTO> data = new ArrayList<>();
				data.add(customer);
				response.setData(data);
				response.addSuccessMessage(Messages.ResponseCustomerController.CUSTOMER_CREATED_SUCCESSFULLY);
		} catch (final CindyCustomException exception) {
			httpStatus = HttpStatus.BAD_REQUEST;

			if (exception.isTechnicalException()) {
				response.addErrorMessage(Messages.ResponseCustomerController.CUSTOMER_CREATED_ERROR);
			} else {
				response.addErrorMessage(exception.getMessage());
			}
			exception.printStackTrace();

		} catch (final Exception exception) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			response.addFatalMessage(Messages.ResponseCustomerController.CUSTOMER_CREATED_UNEXPECTED_ERROR);

			exception.printStackTrace();
		}
		return new ResponseEntity<>(response, httpStatus);
	}

}
