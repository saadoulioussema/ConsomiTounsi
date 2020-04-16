/*
 * Copyright 2017 by Walid YAICH <walid.yaich@esprit.tn>
 * This is an Open Source Software
 * License: http://www.gnu.org/licenses/gpl.html GPL version 3
 */

package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entity.Category;
import tn.esprit.spring.entity.Ray;
import tn.esprit.spring.entity.Type;



//https://docs.spring.io/spring-data/jpa/docs/current/reference/html
public interface RayRepository extends CrudRepository<Ray, Long> {
    @Transactional
    @Modifying
    @Query("update Ray c set c.typeray = ?1, c.categoryray = ?2,c.capacity = ?3 where c.id = ?4")
    void updateRayInfoById(Type typeray, Category categoryray,int capacity, Long Id);
	
   // Ray findById(Long id);//?
    
   // Ray findByTypeAndCategory(Type typeray, Category categoryray);
    
//    @Query("Select c.categoryray from Ray c where c.id=?1")
//    String findCategoryById(Long id);
    
    @Query("Select count(c) from Ray c where c.categoryray=:categoryray")
    long countRaysByCategory(@Param("categoryray") Category categoryray);
    
    @Query("Select c from Ray c where c.categoryray=:categoryray")
    List<Ray> findByCategory(@Param("categoryray")Category categoryray);
    
    
    @Query("Select count(p) From Product p "
			+ "JOIN  p.ray c"
			+ " where c.id=:idray")
    long countProductsInRays(@Param("idray") Long idray);
}