package org.example.prac.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    @NotBlank(message = "제목을 입력하세요")
    private String title;

    @NotNull(message = "개봉 연도를 입력하세요")
    private Integer releaseYear;
}
