package com.dzhanrafetov.melifera.repository;


import com.dzhanrafetov.melifera.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
    List<Image> findImageByAdvertisement_Id(String id);

}

