package ua.edu.chmnu.fks.oop.patterns.adapter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rectangle implements Shape {
    private int width;
    private int height;


    @Override
    public long area() {
        return width*height;
    }
}
