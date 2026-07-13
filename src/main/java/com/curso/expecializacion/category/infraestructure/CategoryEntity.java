package com.curso.expecializacion.category.infraestructure;
import com.curso.expecializacion.product.infraestructure.database.entity.ProductEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "category_entity")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;


    @ManyToMany(mappedBy = "categoryEntities")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<ProductEntity> products = new HashSet<>();

}
