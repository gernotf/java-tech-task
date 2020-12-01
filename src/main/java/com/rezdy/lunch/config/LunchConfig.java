package com.rezdy.lunch.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.rezdy.lunch.strategy.RecipesSortingStrategy;
import com.rezdy.lunch.strategy.RecipesSortingStrategyImpl;

@Configuration
@EntityScan("com.rezdy.lunch.entity")
public class LunchConfig {
	
	@Bean
	@Scope("singleton")
	public RecipesSortingStrategy getRecipesSortingStrategy() {
	    return new RecipesSortingStrategyImpl();
	}
	
}
