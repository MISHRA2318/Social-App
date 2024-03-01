package com.socialLogin.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String captions;
    private String image;
    private String video;


    @JsonIgnore
    @ManyToOne
    private Users users;
    private LocalDateTime createdAt;
//    @JsonIgnore
    @OneToMany
    private List<Users> liked;


    @OneToMany
    private List<Comment> comments = new ArrayList<>();
}
