package org.abhibane.spring.learning.REST.BasicJson.Controller;

import javax.validation.ValidationException;

import org.abhibane.spring.learning.REST.BasicJson.Utils.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class ControllerException {

	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler
	ErrorMessage exceptionHandler(Exception ex) {
		if (ex instanceof ValidationException)
			return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), ex.getMessage());
		else if (ex instanceof NullPointerException)
			return new ErrorMessage(HttpStatus.BAD_GATEWAY.toString(), ex.getMessage());
		else
			return new ErrorMessage(HttpStatus.CONFLICT.toString(), ex.getMessage());
	}
}
