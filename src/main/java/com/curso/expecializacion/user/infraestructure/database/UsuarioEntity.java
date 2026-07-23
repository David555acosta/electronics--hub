package com.curso.expecializacion.user.infraestructure.database;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Table(name = "usuario")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(max = 50)
    private String username;

    @Column(unique = true)
    @Email
    @Size(min = 7, max = 50)
    @NotBlank
    private String email;

    @Column(unique = true)
    @NotBlank
    private String password;


    @ManyToMany(fetch = FetchType.EAGER , targetEntity = RolEntity.class ,cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_roles" ,
            joinColumns = @JoinColumn(name = "user_id") , inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RolEntity> rols;
}
