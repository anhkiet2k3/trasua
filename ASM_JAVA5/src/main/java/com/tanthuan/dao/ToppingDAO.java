package com.tanthuan.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tanthuan.entity.Topping;

public interface ToppingDAO extends JpaRepository<Topping, String>{

}
