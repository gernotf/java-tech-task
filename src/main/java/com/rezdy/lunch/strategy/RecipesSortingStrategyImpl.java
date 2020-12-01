package com.rezdy.lunch.strategy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.rezdy.lunch.entity.IngredientEntity;
import com.rezdy.lunch.entity.RecipeEntity;

/**
 * Implements the strategy, that recipes with a bestBefore date before the prepare date should be put at the end of the list of recipes.
 * 
 */
public class RecipesSortingStrategyImpl implements RecipesSortingStrategy {

	@Override
	public List<RecipeEntity> sort(List<RecipeEntity> recipes, LocalDate prepareDate) {
		if (recipes == null) {
			return null;
		}
		
		// build up a new List to avoid expensive sorting within the given List.
		List<RecipeEntity> sortedRecipies = new ArrayList<>();
		recipes.stream().forEach(recipe -> addToList(recipe, sortedRecipies, prepareDate));
		return Collections.unmodifiableList(sortedRecipies);

	}

	private void addToList(RecipeEntity recipe, List<RecipeEntity> sortedRecipies, LocalDate prepareDate) {
		if (recipe.getIngredients() == null || recipe.getIngredients().isEmpty()) {
			return;
		}
		
		// finds the oldest (smallest) bestBefore date of a recipe
		final Optional<LocalDate> minBestBeforeDateOp = recipe.getIngredients().stream()
				.sorted(Comparator.comparing(IngredientEntity::getBestBefore))
				.findFirst()
				.map(IngredientEntity::getBestBefore);
		
		if (minBestBeforeDateOp.isPresent() && minBestBeforeDateOp.get().isBefore(prepareDate)) {
			// the recipe has at least one ingredient, which bestBefore date is older (or before) than the given prepareDate,
			// thus, this recipe has to be put at the end of the list of possible recipes
			sortedRecipies.add(recipe);
		} else {
			// the bestBefore date is not yet due - put this recipe at the beginning of the list
			sortedRecipies.add(0, recipe);
		}
	}

}
