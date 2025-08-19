package com.javaweb.controlleadvice;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.javaweb.dto.ErrorDetailResponse;
import com.javaweb.myexception.ValidateDataBuildingException;

@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
	@ExceptionHandler(ValidateDataBuildingException.class)
	public ResponseEntity<Object> handleValidateDataBuildingException(ValidateDataBuildingException ex) {
		ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse();
		errorDetailResponse.setError(ex.getMessage());
		errorDetailResponse.setDetail(Arrays.asList("Ten hoac so tang da bi null"));
		return new ResponseEntity<Object>(errorDetailResponse, HttpStatus.BAD_GATEWAY);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception ex) {
		ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse();
		errorDetailResponse.setError(ex.getMessage());
		return new ResponseEntity<Object>(errorDetailResponse, HttpStatus.INTERNAL_SERVER_ERROR  );
	}
}
