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
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("user-order")
    private List<OrderCinema> orderCinemas;

    @ManyToMany(mappedBy ="users",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonManagedReference("users-roles")
    private Set<Role> roles;


    public User() {
    }

    public User(String name, List<OrderCinema> orderCinemas, Set<Role> roles) {
        this.name = name;
        this.orderCinemas = orderCinemas;
        this.roles = roles;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<OrderCinema> getOrders() {
        return orderCinemas;
    }

    public void setOrders(List<OrderCinema> orderCinemas) {
        this.orderCinemas = orderCinemas;
    }
}
