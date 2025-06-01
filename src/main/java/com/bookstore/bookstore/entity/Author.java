package com.bookstore.bookstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authors")
public class Author extends BaseEntity{
    @Column(nullable = false, length = 64)
    private String fullname;

    @OneToMany(mappedBy = "author", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();

}
