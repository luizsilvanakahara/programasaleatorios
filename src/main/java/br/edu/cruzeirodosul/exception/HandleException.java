package br.edu.cruzeirodosul.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class HandleException extends Exception {

	private static final long serialVersionUID = 1L;

	@ExceptionHandler(CustomGenericException.class)
	void handleCustomGenericException(HttpServletRequest req, HttpServletResponse response, CustomGenericException e) throws IOException {
		response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
	}

	@ExceptionHandler(Exception.class)
	void handleException(HttpServletRequest req, HttpServletResponse response, Exception e) throws IOException {
		response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
	}

}
