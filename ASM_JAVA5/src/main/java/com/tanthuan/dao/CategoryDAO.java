package com.tanthuan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tanthuan.entity.Category;

public interface CategoryDAO extends JpaRepository<Category, String>{

}

