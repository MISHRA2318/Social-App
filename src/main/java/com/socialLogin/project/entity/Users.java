package com.socialLogin.project.entity;

import io.micrometer.observation.ObservationFilter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userid;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;
    private Set<Integer> followers=new HashSet<>();
    private Set<Integer> following=new HashSet<>();
    @ManyToMany
    private List<Post> savedPost=new ArrayList<>();
}
