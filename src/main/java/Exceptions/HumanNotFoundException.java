package Exceptions;

public class HumanNotFoundException extends RuntimeException{

    public HumanNotFoundException(Long id){
        super("User with id ["+id+"] is not present in the database");
    }

}
