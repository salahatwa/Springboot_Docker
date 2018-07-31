package com.docker.container.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.docker.container.entities.Product;

/**
 * @author atwa Jul 18, 2018
 */
@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

}
