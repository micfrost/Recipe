package dev.micfro.meals.controllerRest;

import dev.micfro.meals.exceptions.MealException;
import dev.micfro.meals.exceptions.MealNotFoundExceptions;
import dev.micfro.meals.model.Meal;
import dev.micfro.meals.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class MealControllerRest {

    private final MealService mealService;

    @Autowired
    public MealControllerRest(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping("/meals")
    public List<Meal> getMeals() {
        return mealService.getAllMeals();
    }

    @GetMapping("/meals/{mealID}")
    public Meal getMealById(@PathVariable int mealID) {

        if ((mealID >= mealService.getAllMeals().size()+1) || (mealID <= 0)) {
            throw new MealNotFoundExceptions("Meal id not found: " + mealID);
        }
        return mealService.getMealById(mealID);
    }

    // Not found exception
    @ExceptionHandler
    public ResponseEntity<MealException> handleException(MealNotFoundExceptions e) {
        MealException exceptions = new MealException();

        exceptions.setMessage("Oh No. There is something wrong: " + e.getMessage());
        exceptions.setStatus(HttpStatus.NOT_FOUND.value());
        exceptions.setTimeStamp(System.currentTimeMillis()
        );

        return new ResponseEntity<>(exceptions, HttpStatus.NOT_FOUND);
    }

    // any else exception
    @ExceptionHandler
    public ResponseEntity<MealException> handleException(Exception e) {
        MealException exceptions = new MealException();

        exceptions.setMessage(e.getMessage());
        exceptions.setStatus(HttpStatus.BAD_REQUEST.value());
        exceptions.setTimeStamp(System.currentTimeMillis()
        );

        return new ResponseEntity<>(exceptions, HttpStatus.BAD_REQUEST);
    }

}
