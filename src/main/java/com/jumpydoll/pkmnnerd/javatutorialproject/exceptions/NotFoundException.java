package com.jumpydoll.pkmnnerd.javatutorialproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND, reason="Item not found")
public class NotFoundException extends RuntimeException{
}
