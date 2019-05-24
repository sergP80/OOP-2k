package ua.edu.chmnu.fks.oop.lombok;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Data {
    private int count;
    private String str;
}
