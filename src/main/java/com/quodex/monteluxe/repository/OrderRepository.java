package com.quodex.monteluxe.repository;

import com.quodex.monteluxe.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, String> {
}
