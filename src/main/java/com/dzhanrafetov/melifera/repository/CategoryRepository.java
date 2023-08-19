package com.dzhanrafetov.melifera.repository;


import com.dzhanrafetov.melifera.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {



    List<Category> findCategoryByCategoryName(String categoryName);



}
