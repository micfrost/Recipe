package dev.micfro.meals.service;

import dev.micfro.meals.exceptions.MealNotFoundExceptions;
import dev.micfro.meals.model.Meal;
import dev.micfro.meals.repository.MealRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MealService {

    private final MealRepository mealRepository;

    @Autowired
    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @PostConstruct
    public void initMaals() {
        saveMeal(new Meal("Pizza"));
        saveMeal(new Meal("Avocado Bread"));
    }

    // CRUD

    // CREATE
    public void saveMeal(Meal meal) {
        mealRepository.save(meal);
    }



    // READ

    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }

    public Meal getMealById(int mealID) {


        return mealRepository.findById((long) mealID)
                .orElseThrow(() -> new MealNotFoundExceptions("Meal id not found: " + mealID));
    }


    // UPDATE

    // DELETE

}
