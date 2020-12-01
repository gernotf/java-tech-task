package com.rezdy.lunch.strategy;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.rezdy.lunch.entity.IngredientEntity;
import com.rezdy.lunch.entity.RecipeEntity;

public class RecipesSortingStrategieImplTest {

	
	private static final String RECIPE1 = "rec1";
	private static final String RECIPE2 = "rec2";
	private static final String RECIPE3 = "rec3";
	
	private static final String INGREDIENT1 = "ing1";
	private static final String INGREDIENT2 = "ing2";
	private static final String INGREDIENT3 = "ing3";
	
	private static final String DATE1 = "2020-12-14";
	private static final String DATE2 = "2020-12-12";
	private static final String DATE3 = "2020-12-10";
	private static final String DATE4 = "2020-12-05";
	private static final String DATE5 = "2020-12-03";
	private static final String DATE6 = "2020-12-01";

	private static final LocalDate BESTBEFORE1 = LocalDate.parse(DATE1);
	private static final LocalDate BESTBEFORE2 = LocalDate.parse(DATE2);
	private static final LocalDate BESTBEFORE3 = LocalDate.parse(DATE3);
	
	private static final LocalDate USEBY1 = LocalDate.parse(DATE4);
	private static final LocalDate USEBY2 = LocalDate.parse(DATE5);
	private static final LocalDate USEBY3 = LocalDate.parse(DATE6);
	
	private static IngredientEntity ingredient1;
	private static IngredientEntity ingredient2;
	private static IngredientEntity ingredient3;
	
	private static RecipeEntity recipe1;
	private static RecipeEntity recipe2;
	private static RecipeEntity recipe3;
	
	private static List<RecipeEntity> recipes = new ArrayList<>();

	private RecipesSortingStrategyImpl recipeSortingStrategyImpl = new RecipesSortingStrategyImpl();
	
	@BeforeAll
	public static void setUp() {
		ingredient1 = new IngredientEntity();
		ingredient1.setTitle(INGREDIENT1);
		ingredient1.setBestBefore(BESTBEFORE1);
		ingredient1.setUseBy(USEBY1);

		ingredient2 = new IngredientEntity();
		ingredient2.setTitle(INGREDIENT2);
		ingredient2.setBestBefore(BESTBEFORE2);
		ingredient2.setUseBy(USEBY2);
		
		ingredient3 = new IngredientEntity();
		ingredient3.setTitle(INGREDIENT3);
		ingredient3.setBestBefore(BESTBEFORE3);
		ingredient3.setUseBy(USEBY3);

		recipe1 = new RecipeEntity();
		recipe1.setTitle(RECIPE1);
		
		recipe2 = new RecipeEntity();
		recipe2.setTitle(RECIPE2);
		
		recipe3 = new RecipeEntity();
		recipe3.setTitle(RECIPE3);
		
		// put the ingredients into the recipies
		Set<IngredientEntity> ingredients1 = new HashSet<>();
		ingredients1.add(ingredient1);
		ingredients1.add(ingredient2);
		recipe1.setIngredients(ingredients1);
		
		Set<IngredientEntity> ingredients2 = new HashSet<>();
		ingredients2.add(ingredient1);
		ingredients2.add(ingredient2);
		ingredients2.add(ingredient3);
		recipe2.setIngredients(ingredients2);
		
		Set<IngredientEntity> ingredients3 = new HashSet<>();
		ingredients3.add(ingredient3);
		recipe3.setIngredients(ingredients3);

		// reverse order regarding bestBefore date to see, if sorting works correctly
		recipes.add(recipe3);
		recipes.add(recipe2);
		recipes.add(recipe1);
		
	}
	
	@Test
	public void canSortRecipes() {
		LocalDate prepareDate = BESTBEFORE3.plusDays(1L);
		List<RecipeEntity> sortedList = recipeSortingStrategyImpl.sort(recipes, prepareDate);
		
		// bestBefore date of ingredients 3 is in the past - recipe 2 and 3 and must be at the end of the sorted list
		assertTrue(sortedList.indexOf(recipe1) < sortedList.indexOf(recipe2));
		assertTrue(sortedList.indexOf(recipe1) < sortedList.indexOf(recipe3));
		
	}
}
