package com.example.SpringMVC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.SpringMVC.model.WatchList;

@Repository
public interface WatchListRepository extends JpaRepository<WatchList,String>{

}
