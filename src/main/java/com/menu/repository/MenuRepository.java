package com.menu.repository;

import com.menu.model.MenuModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Shikhar on 02/01/18.
 */
public interface MenuRepository extends CrudRepository<MenuModel, Long> {

    //CatalogModel findByCustEmailidAndCustPasswordAndActiveStatus(String custEmailId, String custPassword, boolean activeStatus);
	//CatalogModel findByCustEmailidAndSecurityQuesAns(String custEmailId, String securityQuesAns);
	MenuModel findByMenuId(long itemid);
    MenuModel findByMenuName(String itemName);
    Iterable<MenuModel> findAll();

}
