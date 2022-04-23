package com.example.blog.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @project: Blog
 * @author: Sarvar55
 */
/*
 * https://ashishontech.xyz/jackson-json-infinite-recursion-problem/
 * */
/*
 *  Cascade, bir JPA standardıdır.
 *  Entity sınıflarımızdaki ilişkilerin hareketlerini yani davranışlarını cascade tipleri ile ayarlarız.
 *  Yani ilişkili sınıfların birbirlerinden etkilenip etkilenmemesini sağlıyor.
 *  Örnek olarak bir değer sildiğimizde o silinen veri ilişkili olan verilerin etkilenmesini ya da etkilenmemesini sağlarız.
 * Bu JPA standartı, veritabanımıza bulaşmadan kolaylıkla Java sınıflarımız üzerinden işlemleri yönetmemizi sağlar.
 * */
    /*
    * Since we are using OneToMany and ManyToOne mapping. User => UserDetail => User

When we query User, Hibernate will try to get UserDetail as well. As UserDetail contains a reference to User, it will try to get User and
* this will go on in loop causing StackOverflowError exception.
    *
    *  Adding fetch=FetchType.LAZY will not work for these scenarios.
    *
    *
    *
    * */
@Entity(name = "Category")
@Table(name = "categories")
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "products"})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @Column(name = "title", length = 100, nullable = false)
    private String categoryTitle;

    @Column(name = "descrpition")
    private String categoryDescription;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Post> posts = new ArrayList<>();

}
