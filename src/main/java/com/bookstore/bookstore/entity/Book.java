package com.bookstore.bookstore.entity;

import com.bookstore.bookstore.enums.BindType;
import com.bookstore.bookstore.enums.LanguageCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "books")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Book extends BaseEntity{
    @Column(nullable = false, unique = false, length = 128)
    private String title;

    @Column(nullable = false, unique = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private String cover;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private int pageCount;

    @Column(nullable = false, unique = true, length = 17)
    private String isbn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discount_id")
    private Discount discount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BindType bindType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LanguageCode langCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_categories",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<WishlistItem> wishlistItems = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CartItem> cartItems = new ArrayList<>();

    @Column(nullable = false)
    private int stockQuantity;

    @Column(nullable = false)
    private int totalSoldQuantity = 0;

    @Column(nullable = false)
    private int monthlySoldQuantity = 0;

    @Column(nullable = false)
    private Long ratingCount = 0L;

    @Column(nullable = false, precision = 3, scale = 2)
    private BigDecimal averageRating = BigDecimal.ZERO;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();
}

