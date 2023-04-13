package com.springbootemail.application.util;


import com.springbootemail.application.constants.ErrorCodes;
import com.springbootemail.application.exception.FinalException;
import com.springbootemail.application.response.FinalResponse;

public class ResponseGeneratorUtil {

	public static FinalResponse okResponse(Object data) {
		return new FinalResponse(ErrorCodes.OK_STATUS, ErrorCodes.OK_STATUS_MESSAGE, data);
	}

	public static FinalResponse genericErrorResponse() {
		return new FinalResponse(ErrorCodes.GENERIC_ERROR, ErrorCodes.GENERIC_ERROR_MESSAGE);
	}

	public static FinalResponse genericNotFoundResponse() {
		return new FinalResponse(ErrorCodes.GENERIC_NOT_FOUND, ErrorCodes.GENERIC_NOT_FOUND_MESSAGE);
	}

	public static FinalResponse finalExceptionResponse(FinalException fex) {
		return new FinalResponse(fex.getCode(), fex.getMessage());
	}

	public static FinalResponse genericErrorResponse(FinalException fex) {
		return new FinalResponse(fex.getCode(), fex.getMessage());
	}
}
