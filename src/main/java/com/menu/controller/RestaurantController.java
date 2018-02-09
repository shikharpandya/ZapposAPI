package com.menu.controller;

import com.menu.model.RestaurantModel;
import com.menu.repository.RestaurantRepository;
import com.menu.repository.MenuRepository;
import com.menu.repository.MenuItemRepository;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * Created by shikhar on 02/02/18.
 */
@CrossOrigin
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    //private final static Logger logger = Logger.getLogger(CartController.class);

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @RequestMapping(value = "/getRestaurant", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getRestaurants(@RequestParam("restName") String restName) throws Exception {
        //if(!EasyShopUtil.isValidCustomer(userRepository, request)){
        //  return ResponseEntity.badRequest().body("Invalid Auth Token");
        //}
        JSONObject response = new JSONObject();
        JSONObject getRestaurants = new JSONObject();
        RestaurantModel restaurantModel = restaurantRepository.findByRestName(restName);
        if (restaurantModel != null)
        {
            response.put("status", true);
            response.put("restName", restaurantModel.getRestName());
            getRestaurants.put("getRestaurant", response);
            return ResponseEntity.ok(getRestaurants.toString());
        }
        else
        {
            response.put("status", false);
            response.put("message", "Restaurant not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response.toString());
        }
    }


    @RequestMapping(value = "/getAllRestaurants", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getAllRestaurants() throws Exception{
        JSONObject response = new JSONObject();
        JSONObject singleItem = new JSONObject();
        JSONObject getAllItem = new JSONObject();
        //CatalogModel catalogModel = catalogRepository.findByItemName(itemName);
        List <RestaurantModel> restaurants = null;
        if(restaurantRepository.findAll() != null) {
            for(RestaurantModel restaurant: restaurantRepository.findAll()) {
                restaurants.add(restaurant);
                singleItem.put("restaurantId",restaurant.getRestId());
                singleItem.put("restaurantName",restaurant.getRestName());
                singleItem.put("restaurantPriceFor2",restaurant.getRestPriceFor2());
                singleItem.put("restaurantDescription",restaurant.getRestDescription());
                singleItem.put("restCuisine",restaurant.getRestCuisine());
                singleItem.put("restRating",restaurant.getRestRating());
            }
        }
        else {
            response.put("status", false);
            response.put("message","Item not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response.toString());
        }
        response.put("status", true);
        response.put("item",singleItem);
        getAllItem.put("getAllItem",response);
        return ResponseEntity.ok(getAllItem.toString());
    }



    @RequestMapping(value = "/addRestaurant", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity addRestaurant(@Valid @RequestBody RestaurantModel restaurantModel) throws Exception{
        /*
        if(!EasyShopUtil.isValidCustomer(userRepository, request)){
            return ResponseEntity.badRequest().body("Invalid Auth Token");
        }
        */
        JSONObject response = new JSONObject();
        restaurantRepository.save(restaurantModel);
        response.put("status",true);
        //response.put("restaurantCount",commonRepository.getCartCount(cartModel.getCustId()).size());
        return ResponseEntity.ok(response.toString());
    }
}
