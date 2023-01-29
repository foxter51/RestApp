package Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class HumanNotFoundException extends RuntimeException{

    public HumanNotFoundException(Long id){
        super("User with id ["+id+"] is not present in the database");
    }

}
