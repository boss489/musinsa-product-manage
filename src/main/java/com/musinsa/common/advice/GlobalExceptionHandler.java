package com.musinsa.common.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.musinsa.common.exception.NotFoundException;
import com.musinsa.v1.common.CommonResponseModel;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public CommonResponseModel handleNotFoundException(NotFoundException ex, WebRequest request) {
		log.error("Validation 오류", ex);
		return CommonResponseModel.builder()
			.returnCode(HttpStatus.BAD_REQUEST.value())
			.message(ex.getMessage())
			.build();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public CommonResponseModel handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
		String errMsg = ex.getBindingResult()
			.getFieldErrors()
			.stream()
			.map(error -> error.getDefaultMessage())
			.collect(Collectors.joining("\n"));
		log.error("Validation 오류", ex);

		return CommonResponseModel.builder()
			.returnCode(HttpStatus.BAD_REQUEST.value())
			.message(errMsg)
			.build();

	}

	@ExceptionHandler({HttpMessageNotReadableException.class, JsonProcessingException.class})
	public CommonResponseModel handleJsonParseException(Exception ex, WebRequest request) {
		log.error("Json 파싱 오류", ex);
		return CommonResponseModel.builder()
			.returnCode(HttpStatus.BAD_REQUEST.value())
			.message("임력값이 유효하지 않습니다.")
			.build();
	}

	@ExceptionHandler(Exception.class)
	public CommonResponseModel handleGlobalException(Exception ex) {
		log.error("서버 오류", ex);
		return CommonResponseModel.builder()
			.returnCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
			.message("오류가 발생했습니다")
			.build();
	}
}
