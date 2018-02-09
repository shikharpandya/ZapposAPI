package com.menu.controller;

import com.menu.model.MenuModel;
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
@RequestMapping("/menu")
public class MenuController {

    //private final static Logger logger = Logger.getLogger(CartController.class);

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @RequestMapping(value = "/getMenu", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getRestaurants(@RequestParam("menuName") String menuName) throws Exception {
        //if(!EasyShopUtil.isValidCustomer(userRepository, request)){
        //  return ResponseEntity.badRequest().body("Invalid Auth Token");
        //}
        JSONObject response = new JSONObject();
        JSONObject getMenus = new JSONObject();
        MenuModel menuModel = menuRepository.findByMenuName(menuName);
        if (menuModel != null)
        {
            response.put("status", true);
            response.put("restName", menuModel.getMenuName());
            getMenus.put("getMenu", response);
            return ResponseEntity.ok(getMenus.toString());
        }
        else
        {
            response.put("status", false);
            response.put("message", "Restaurant not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response.toString());
        }
    }


    @RequestMapping(value = "/getAllMenu", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getAllMenus() throws Exception{
        JSONObject response = new JSONObject();
        JSONObject singleItem = new JSONObject();
        JSONObject getAllItem = new JSONObject();

        List <MenuModel> menus = null;

        if(menuRepository.findAll() != null) {
            for(MenuModel menu: menuRepository.findAll()) {
                menus.add(menu);
                singleItem.put("menuId",menu.getMenuId());
                singleItem.put("menuName",menu.getMenuName());
                singleItem.put("menuDescription",menu.getMenuDescription());
                singleItem.put("menuSection",menu.getMenuSection());
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



    @RequestMapping(value = "/addMenu", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity addMenu(@Valid @RequestBody MenuModel menuModel) throws Exception{
        /*
        if(!EasyShopUtil.isValidCustomer(userRepository, request)){
            return ResponseEntity.badRequest().body("Invalid Auth Token");
        }
        */
        JSONObject response = new JSONObject();
        menuRepository.save(menuModel);
        response.put("status",true);
        //response.put("restaurantCount",commonRepository.getCartCount(cartModel.getCustId()).size());
        return ResponseEntity.ok(response.toString());
    }
}
