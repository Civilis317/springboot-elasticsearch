package nl.playground.demo.elasticsearch.client.rest.controller;


import nl.playground.demo.elasticsearch.client.storage.DocumentExistsException;
import nl.playground.demo.elasticsearch.client.storage.StorageException;
import nl.playground.demo.elasticsearch.client.storage.DocumentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AbstractController {


    @ExceptionHandler
    protected void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), "The request was not valid");
    }

    @ExceptionHandler
    protected void handleNotFoundException(DocumentNotFoundException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), "The requested document was not found");
    }

    @ExceptionHandler
    protected void handleEntityExistsException(DocumentExistsException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.UNPROCESSABLE_ENTITY.value(), "The indicated document already exists");
    }

    @ExceptionHandler
    protected void handleStorageException(StorageException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An internal server-error caused the request to not be processed");
    }
}
