package com.socialLogin.project.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedUsers {
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
}
