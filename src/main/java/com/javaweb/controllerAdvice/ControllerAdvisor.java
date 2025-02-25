package com.javaweb.controllerAdvice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.javaweb.customException.FieldRequiredException;
import com.javaweb.model.ErrorResponseDTO;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
	 @ExceptionHandler(FieldRequiredException.class)
	public ResponseEntity<Object> handleArithmeticException(
			FieldRequiredException ex, WebRequest request) {
		 
		 ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
		 errorResponseDTO.setError(ex.getMessage());
		 List<String> details = new ArrayList<String>();
		 details.add("Error");
		 errorResponseDTO.setDetail(details);
		 
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
    } 
}	
