package ua.edu.chmnu.fks.oop.patterns.adapter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Square implements Shape {

    private int size;


    public static void main(String[] args) {
        Shape s = new Square(100);
        System.out.println(s.area());
    }

    @Override
    public long area() {
        return size*size;
    }
}
