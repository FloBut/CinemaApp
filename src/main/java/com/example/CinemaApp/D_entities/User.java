package com.example.CinemaApp.D_entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String name;
    @Column
    private String password;
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("user-orderCinema")
    private List<OrderCinema> orderCinemas;

    @ManyToMany(mappedBy ="users",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonManagedReference("users-roles")
    private Set<Role> roles;


    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<OrderCinema> getOrderCinemas() {
        return orderCinemas;
    }

    public void setOrderCinemas(List<OrderCinema> orderCinemas) {
        this.orderCinemas = orderCinemas;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
