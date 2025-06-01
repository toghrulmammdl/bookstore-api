package com.bookstore.bookstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "publishers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Publisher extends BaseEntity{
    @Column(nullable = false, unique = true, length = 200)
    private String name;

    @OneToMany(mappedBy = "publisher", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();
}
