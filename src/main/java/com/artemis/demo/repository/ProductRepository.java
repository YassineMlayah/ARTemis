package com.artemis.demo.repository;

import com.artemis.demo.model.Product;
import com.artemis.demo.model.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByArtistUserID(Long userId);

    @Query("SELECT p FROM Product p WHERE "
     + "(:minPrice IS NULL OR p.price >= :minPrice) AND "
     + "(:maxPrice IS NULL OR p.price <= :maxPrice) AND "
     + "(:category IS NULL OR :category = 'all' OR p.category = :category) AND "
     + "(:artistName IS NULL OR LOWER(CONCAT(p.artist.firstName, ' ', p.artist.lastName)) LIKE LOWER(CONCAT('%', :artistName, '%')))")
    List<Product> filterProducts(
        @Param("minPrice") Double minPrice,
        @Param("maxPrice") Double maxPrice,
        @Param("category") Category category,
        @Param("artistName") String artistName);

    @Query("SELECT p FROM Product p ORDER BY p.available DESC, p.price DESC")
    List<Product> findTop6Products(Pageable pageable);
}