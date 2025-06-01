package com.bookstore.bookstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category extends BaseEntity{
    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "super_id")
    private Category superCategory;

    @OneToMany(mappedBy = "superCategory", cascade = CascadeType.MERGE, orphanRemoval = true)
    private Set<Category> subCategories = new HashSet<>();

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private Set<Book> books = new HashSet<>();

    @Column(columnDefinition = "TEXT")
    private String description;

}
