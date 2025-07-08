package org.example.prac.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoDTO {
    private Integer id;

    @NotBlank(message = "제목을 입력하세요")
    private String title;
    private boolean completed;
}
