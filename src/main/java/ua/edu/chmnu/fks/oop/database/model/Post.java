package ua.edu.chmnu.fks.oop.database.model;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Post {
    private Long id;
    @EqualsAndHashCode.Include
    private String title;
    @EqualsAndHashCode.Include
    private String content;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    private User user;
}
