package com.curso.expecializacion.review.infraestructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QueryReviewRepository extends JpaRepository<ReviewEntity, Integer> {
}
