package ua.edu.chmnu.fks.oop.database.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Quote {
    private Long id;
    private String content;
    private LocalDateTime createdTime;
    private Post post;
    private User user;
}
