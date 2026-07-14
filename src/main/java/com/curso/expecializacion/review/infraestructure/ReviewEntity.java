package com.curso.expecializacion.review.infraestructure;
import com.curso.expecializacion.product.infraestructure.database.entity.ProductEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "reviews")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String comment;
    private Integer source;

    @ManyToOne
    @JoinColumn(name = "productos_codigo")
    @org.hibernate.annotations.OnDelete(action = org.hibernate.annotations.OnDeleteAction.CASCADE)
    private ProductEntity productEntity;
}
