package com.socialLogin.project.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Integer userid;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private Set<Integer> followers=new HashSet<>();
    private Set<Integer> following=new HashSet<>();
}
