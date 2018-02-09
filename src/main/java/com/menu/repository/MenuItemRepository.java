package com.menu.repository;

import com.menu.model.MenuItemModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Iterator;
import java.util.List;

/**
 * Created by admin on 22/10/16.
 */
public interface MenuItemRepository extends CrudRepository<MenuItemModel, Long> {

    //CatalogModel findByCustEmailidAndCustPasswordAndActiveStatus(String custEmailId, String custPassword, boolean activeStatus);
	//CatalogModel findByCustEmailidAndSecurityQuesAns(String custEmailId, String securityQuesAns);
	MenuItemModel findByItemId(long itemid);
    MenuItemModel findByItemName(String itemName);
    Iterable<MenuItemModel> findAll();

}
