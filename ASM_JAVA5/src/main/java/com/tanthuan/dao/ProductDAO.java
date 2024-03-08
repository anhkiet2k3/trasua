package com.tanthuan.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tanthuan.entity.Category;
import com.tanthuan.entity.Product;

public interface ProductDAO extends JpaRepository<Product, String>{
	@Query("SELECT o FROM Product o WHERE o.categoryID.categoryID LIKE ?1")
	List<Product> findByCategory(String categoryID);
	
	@Query("SELECT o from Product o ORDER BY o.productID ASC")
	Page<Product> findAllAsc(Pageable pageable);
	
	@Query("SELECT o from Product o WHERE o.productID like %?1% or productName like %?1% or categoryID like %?1% or description like %?1%")
	Page<Product> findProductByString(String content, Pageable pageable);

	@Query("SELECT o from Product o WHERE o.price = ?1")
	Page<Product> findProductByPrice(Float content, Pageable pageable);
	
	@Query("SELECT o from Product o WHERE o.size = ?1")
	Page<Product> findProductBySize(Boolean content, Pageable pageable);
}
