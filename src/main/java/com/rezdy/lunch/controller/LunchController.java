package com.rezdy.lunch.controller;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rezdy.lunch.entity.RecipeEntity;
import com.rezdy.lunch.exception.LunchExceptionHandler;
import com.rezdy.lunch.service.LunchService;

/**
 * Handles the REST requests.
 *
 */
@RestController
public class LunchController {

    private final LunchService lunchService;

    @Autowired
    public LunchController(LunchService lunchService) {
        this.lunchService = lunchService;
    }

    /**
     * Gets a prepare date on which a list of relevant recipes is assembled and returned. Malformatted date parameter are handled in {@linkplain LunchExceptionHandler}
     * @param date The prepare date given to collect and provide relevant recipes, allowed format: YYYY-MM-DD.
     */
    @GetMapping(value = "/lunch", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getRecipes(@RequestParam(value = "date", required = true) String date) {
    	
        List<RecipeEntity> recipes = lunchService.getNonExpiredRecipesOnDate(LocalDate.parse(date));
        
        final ObjectMapper mapper = new ObjectMapper();
        String lunchesJson = StringUtils.EMPTY;
		try {
			lunchesJson = mapper.writeValueAsString(recipes);
		} catch (JsonProcessingException e) {
			lunchesJson = "{ \"no lunches could be found\" }";
			// do some more useful logging with a logger instance, here
			e.printStackTrace();
		}
        
        return lunchesJson;
    }
}
