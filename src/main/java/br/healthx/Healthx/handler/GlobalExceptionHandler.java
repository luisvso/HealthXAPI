package br.healthx.Healthx.handler;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.graphql.GraphQlProperties.Http;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.healthx.Healthx.paciente.model.exception.EmailAlreadyExistsException;
import br.healthx.Healthx.paciente.model.exception.InvalidBirthDateException;
import br.healthx.Healthx.paciente.model.exception.PacienteNotFoundException;
import br.healthx.Healthx.psychologist.model.exception.PsychologistAlreadyExistsException;
import br.healthx.Healthx.psychologist.model.exception.PsychologistNotFoundException;
import br.healthx.Healthx.session.dto.ErrorResponse;
import br.healthx.Healthx.session.model.exception.ResourceNotFoundException;
import br.healthx.Healthx.session.model.exception.SessionDateException;
import br.healthx.Healthx.session.model.exception.SessionHourException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

// RestControllerAdvice combines ControllerAdvice + ResponseBody 
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SessionDateException.class)
    public ResponseEntity<ErrorResponse> handleSessionDate(SessionDateException ex, HttpServletRequest servletRequest) {

        log.error("Exception Caught {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                servletRequest.getRequestURI(),
                Collections.singletonMap(" ", ex.getMessage()),
                LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex,
            HttpServletRequest httpServletRequest) {

        log.error("Exception caught {} ", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                httpServletRequest.getRequestURI(),
                Collections.singletonMap(" ", ex.getMessage()),
                LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotValid(MethodArgumentNotValidException ex,
            HttpServletRequest httpServletRequest) {

        log.error("Exception caught {} ", ex.getMessage());

        Map<String, String> fieldErrors = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(error -> fieldErrors.put(error.getObjectName(), error.getDefaultMessage()));

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                httpServletRequest.getRequestURI(),
                fieldErrors,
                LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegridy(DataIntegrityViolationException ex,
            HttpServletRequest httpServletRequest) {

        log.error("Exception caught {} ", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                httpServletRequest.getRequestURI(),
                Collections.singletonMap(" ", ex.getMessage()),
                LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex,
            HttpServletRequest htp) {

        log.error("Exception caught {} ", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                htp.getRequestURI(),
                Collections.singletonMap(" ", ex.getMessage()),
                LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArguments(IllegalArgumentException ex, HttpServletRequest htp) {

        log.error("Exception caught {} ", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                htp.getRequestURI(),
                Collections.singletonMap(" ", ex.getMessage()),
                LocalDateTime.now()

        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // @ExceptionHandler(Exception.class)
    // public ResponseEntity<ErrorResponse> handleException(Exception ex,
    // HttpServletRequest httpServletRequest) {
    //
    // log.error("Exception caught {} ", ex.getMessage());
    //
    // ErrorResponse errorResponse = new ErrorResponse(
    // HttpStatus.INTERNAL_SERVER_ERROR.value(),
    // HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
    // "Internal server error",
    // httpServletRequest.getRequestURI(),
    // Collections.singletonMap(" ", ex.getMessage()),
    // LocalDateTime.now());
    //
    // return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    //
    // }

    // Exception, IllegalArgumentException, ConstraintViolationException,
    // DataIntegrityViolationException, MethodArgumentNotValidException,
    // ResourceNotFoundException, SessionDateException ,
    // EmailAlreadyExistsException, InvalidBirthDateException,
    // PacienteNotFoundException

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailExists(EmailAlreadyExistsException ex,
            HttpServletRequest httpServletRequest) {

        log.error("Exception caught {} ", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                "Intrernal Server Error this email is already registered",
                httpServletRequest.getRequestURI(),
                Collections.singletonMap(" ", ex.getMessage()),
                LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidBirthDateException.class)
    public ResponseEntity<ErrorResponse> handleInvalidBirthDate(InvalidBirthDateException ex,
            HttpServletRequest httpServletRequest) {

        log.error("Exception caught {} ", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Bad request this birth date is invalid, please verify",
                httpServletRequest.getRequestURI(),
                Collections.singletonMap(" ", ex.getMessage()),
                LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PacienteNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePacienteNotFound(PacienteNotFoundException ex,
            HttpServletRequest httpServletRequest) {

        log.error("Exception caught : {} of name : {}", ex.getMessage(), ex.getClass());

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "Internal server error, this patient does not exit",
                httpServletRequest.getRequestURI(),
                Collections.singletonMap(" ", ex.getMessage()),
                LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PsychologistAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handlePsychologistAlreadyExist(PsychologistAlreadyExistsException psyException,
            HttpServletRequest httpServletRequest) {

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "This psychologist already exists",
                httpServletRequest.getRequestURI(),
                Collections.singletonMap(" ", psyException.getMessage()),
                LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PsychologistNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePsychologistNotFound(PsychologistNotFoundException ex,
            HttpServletRequest httpServletRequest) {

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "This psychologist was not found",
                httpServletRequest.getRequestURI(),
                Collections.singletonMap(" ", ex.getMessage()),
                LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SessionHourException.class)
    public ResponseEntity<ErrorResponse> handleHourException(SessionHourException exception, HttpServletRequest http) {

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                "This is a conflict hour Exception",
                http.getRequestURI(),
                Collections.singletonMap(" ", exception.getMessage()),
                LocalDateTime.now()

        );

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

}
