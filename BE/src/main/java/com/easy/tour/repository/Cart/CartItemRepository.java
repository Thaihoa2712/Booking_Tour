package com.easy.tour.repository.Cart;

import com.easy.tour.entity.Cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Query("SELECT ci FROM CartItem ci WHERE ci.tour.tourCode = :tourCode")
    List<CartItem> findByTourCode(@Param("tourCode") String tourCode);
}
