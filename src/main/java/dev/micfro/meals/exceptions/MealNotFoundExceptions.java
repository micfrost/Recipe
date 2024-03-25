package dev.micfro.meals.exceptions;

public class MealNotFoundExceptions extends RuntimeException { // custom exception class

    public MealNotFoundExceptions(String message){
        super(message);
    }
}
