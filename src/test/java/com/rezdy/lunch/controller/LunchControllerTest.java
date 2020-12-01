package com.rezdy.lunch.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.rezdy.lunch.entity.RecipeEntity;
import com.rezdy.lunch.service.LunchService;

@SpringBootTest
public class LunchControllerTest {

    private static final String DATE_PARAM = "date";

	private static final String DATE = "2030-01-01";

	private static MockMvc mvc;

    private static LunchService lunchService;
    
    @InjectMocks
    private static LunchController lunchController;
    
    @Captor
    private ArgumentCaptor<LocalDate> localDateCaptor;

    @BeforeAll
    public static void setup() {
    	lunchService = mock(LunchService.class, RETURNS_DEEP_STUBS);
        lunchController = new LunchController(lunchService);
        mvc = MockMvcBuilders.standaloneSetup(lunchController).build();
    }

    @Test
    public void canGetLunch() throws Exception {
    	// when
    	when(lunchService.getNonExpiredRecipesOnDate(any())).thenReturn(new ArrayList<RecipeEntity>());
    	
    	// then
    	mvc.perform(get("/lunch")
    	            .param(DATE_PARAM, DATE))
    	        .andExpect(status().isOk());

        verify(lunchService).getNonExpiredRecipesOnDate(localDateCaptor.capture());
        assertTrue(localDateCaptor.getValue().equals(LocalDate.parse(DATE)));
    }

}
