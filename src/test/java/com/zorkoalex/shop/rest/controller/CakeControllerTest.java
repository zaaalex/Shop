package com.zorkoalex.shop.rest.controller;

import com.zorkoalex.shop.dto.Cake;
import com.zorkoalex.shop.dto.Cakes;
import com.zorkoalex.shop.goods.CakesService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.AssertionErrors;

import java.util.Collections;

import static net.bytebuddy.matcher.ElementMatchers.any;


public class CakeControllerTest {

    @Test
    void testCakes(){
       /* Cakes cakes = new Cakes();
        cakes.setCakeList(Collections.emptyList());
        CakesService cakesService = Mockito.mock(CakesService.class);
        Mockito.doReturn(cakes).when(cakesService).getCakes();
        CakeController cakeController = new CakeController(cakesService);
        Cakes cakesTest = cakeController.cakes();
        AssertionErrors.assertEquals("", cakesTest,cakes);
        Mockito.verify (cakesService, Mockito.times(1)).getCakes();*/
    }
}
