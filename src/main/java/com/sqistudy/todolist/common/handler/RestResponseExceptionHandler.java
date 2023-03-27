package com.sqistudy.todolist.common.handler;

import com.sqistudy.todolist.web.dto.ErrorDTO;
import com.sqistudy.todolist.common.exception.DuplicateMemberException;
import com.sqistudy.todolist.common.exception.NotFoundMemberException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(CONFLICT)
    @ExceptionHandler(value = { DuplicateMemberException.class })
    @ResponseBody
    protected ErrorDTO conflict(RuntimeException ex, WebRequest request) {
        return new ErrorDTO(CONFLICT.value(), ex.getMessage());
    }

    @ResponseStatus(FORBIDDEN)
    @ExceptionHandler(value = { NotFoundMemberException.class, AccessDeniedException.class })
    @ResponseBody
    protected ErrorDTO forbidden(RuntimeException ex, WebRequest request) {
        return new ErrorDTO(FORBIDDEN.value(), ex.getMessage());
    }
}
