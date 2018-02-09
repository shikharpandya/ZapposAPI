package com.menu.controller;

import com.menu.model.MenuItemModel;
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
public class MenuItemController {

    //private final static Logger logger = Logger.getLogger(CartController.class);

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @RequestMapping(value = "/getItem", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getItem(@RequestParam("itemName") String itemName) throws Exception {
        //if(!EasyShopUtil.isValidCustomer(userRepository, request)){
        //  return ResponseEntity.badRequest().body("Invalid Auth Token");
        //}
        JSONObject response = new JSONObject();
        JSONObject getMenuItems = new JSONObject();
        MenuItemModel menuItemModel = menuItemRepository.findByItemName(itemName);
        if (menuItemModel != null)
        {
            response.put("status", true);
            response.put("itemName", menuItemModel.getItemName());
            getMenuItems.put("getMenuItem", response);
            return ResponseEntity.ok(getMenuItems.toString());
        }
        else
        {
            response.put("status", false);
            response.put("message", "Restaurant not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response.toString());
        }
    }


    @RequestMapping(value = "/getAllMenuItems", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getAllMenuItems() throws Exception{
        JSONObject response = new JSONObject();
        JSONObject singleItem = new JSONObject();
        JSONObject getAllItem = new JSONObject();
        //CatalogModel catalogModel = catalogRepository.findByItemName(itemName);
        List <MenuItemModel> items = null;
        if(menuItemRepository.findAll() != null) {
            for(MenuItemModel item: menuItemRepository.findAll()) {
                items.add(item);
                singleItem.put("itemId",item.getItemId());
                singleItem.put("itemName",item.getItemName());
                singleItem.put("itemDescription",item.getItemDescription());
                singleItem.put("itemPrice",item.getItemPrice());
                singleItem.put("itemQuantity",item.getItemQuantity());
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



    @RequestMapping(value = "/addItem", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity addItem(@Valid @RequestBody MenuItemModel menuItemModel) throws Exception{
        /*
        if(!EasyShopUtil.isValidCustomer(userRepository, request)){
            return ResponseEntity.badRequest().body("Invalid Auth Token");
        }
        */
        JSONObject response = new JSONObject();
        menuItemRepository.save(menuItemModel);
        response.put("status",true);
        //response.put("restaurantCount",commonRepository.getCartCount(cartModel.getCustId()).size());
        return ResponseEntity.ok(response.toString());
    }
}
