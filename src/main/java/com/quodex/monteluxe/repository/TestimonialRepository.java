package com.quodex.monteluxe.repository;

import com.quodex.monteluxe.model.Testimonial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestimonialRepository extends JpaRepository<Testimonial, String> {
}
