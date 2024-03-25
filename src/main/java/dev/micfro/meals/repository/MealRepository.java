package dev.micfro.meals.repository;

import dev.micfro.meals.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Long> {
}
