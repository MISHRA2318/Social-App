package com.socialLogin.project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String chat_name;
    private String chat_image;
    private LocalDateTime timestamp;
    @ManyToMany
    private List<Users>users=new ArrayList<>();
    @OneToMany
    private List<Message> messages = new ArrayList<>();
}
