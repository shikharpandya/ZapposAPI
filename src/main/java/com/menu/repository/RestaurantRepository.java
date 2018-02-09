package com.menu.repository;

import com.menu.model.RestaurantModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Iterator;
import java.util.List;

/**
 * Created by admin on 22/10/16.
 */
public interface RestaurantRepository extends CrudRepository<RestaurantModel, Long> {

	RestaurantModel findByRestId(long itemid);
    RestaurantModel findByRestName(String restName);
    Iterable<RestaurantModel> findAll();

}
