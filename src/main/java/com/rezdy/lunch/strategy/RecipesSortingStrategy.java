package com.rezdy.lunch.strategy;

import java.time.LocalDate;
import java.util.List;

import com.rezdy.lunch.entity.RecipeEntity;

public interface RecipesSortingStrategy {

	/**
	 * Sorts a list of recipes using a certain rule.
	 * @param recipes The List of recipes to be sorted.
	 * @param prepareDate The date on which the recipe wants to be prepared
	 * @return A sorted list of recipes according to the implemented rule. The amount of elements in this list is equal to the amount of the given list.
	 */
	List<RecipeEntity> sort(List<RecipeEntity> recipes, LocalDate prepareDate);

}
