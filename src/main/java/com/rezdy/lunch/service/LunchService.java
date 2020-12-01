package com.rezdy.lunch.service;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rezdy.lunch.entity.RecipeEntity;
import com.rezdy.lunch.strategy.RecipesSortingStrategy;

/**
 * A service class for Lunch calculations.
 * 
 */
@Service
public class LunchService {

    private static final String INGREDIENTS_COLUMN_USEBY  = "useBy";
    private static final String RECIPE_COLUMN_TITLE       = "title";
    private static final String RECIPE_COLUMN_INGREDIENTS = "ingredients";

	@Autowired
    private EntityManager entityManager;

    @Autowired
    private RecipesSortingStrategy RecipesSortingStrategy;

    /**
     * Collects and provides a list with recipes according to the given (prepare) date.
     * There are only recipes which have no ingredient which use-by date has expired.
     * Recipes with ingredients, which best-before date is expires are put at the end of the returned list.
     * @param prepareDate The date
     * @return A list of recipes with no expired (use-by) ingredients and with expired best-before dates at the end.
     */
    public List<RecipeEntity> getNonExpiredRecipesOnDate(final LocalDate prepareDate) {
        List<RecipeEntity> recipes = loadRecipes(prepareDate);
        return sortRecipes(recipes, prepareDate);
    }

    private List<RecipeEntity> loadRecipes(final LocalDate prepareDate) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RecipeEntity> criteriaQuery = criteriaBuilder.createQuery(RecipeEntity.class);
        Root<RecipeEntity> recipeRoot = criteriaQuery.from(RecipeEntity.class);

        CriteriaQuery<RecipeEntity> query = criteriaQuery.select(recipeRoot);

        Subquery<RecipeEntity> nonExpiredIngredientSubquery = query.subquery(RecipeEntity.class);
        Root<RecipeEntity> nonExpiredIngredientRoot = nonExpiredIngredientSubquery.from(RecipeEntity.class);
        nonExpiredIngredientSubquery.select(nonExpiredIngredientRoot);

        Predicate matchingRecipePredicate = criteriaBuilder.equal(nonExpiredIngredientRoot.get(RECIPE_COLUMN_TITLE), recipeRoot.get(RECIPE_COLUMN_TITLE));
        Predicate nonExpiredIngredientPredicate = criteriaBuilder.greaterThan(nonExpiredIngredientRoot.join(RECIPE_COLUMN_INGREDIENTS).get(INGREDIENTS_COLUMN_USEBY), prepareDate);

        Predicate allNonExpiredIngredientsPredicate = criteriaBuilder.exists(nonExpiredIngredientSubquery.where(matchingRecipePredicate, nonExpiredIngredientPredicate));

        return entityManager.createQuery(query.where(allNonExpiredIngredientsPredicate)).getResultList();
    }
    
    private List<RecipeEntity> sortRecipes(final List<RecipeEntity> recipes, final LocalDate prepareDate) {
    	return RecipesSortingStrategy.sort(recipes, prepareDate);
    }
}
