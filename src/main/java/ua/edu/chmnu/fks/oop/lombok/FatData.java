package ua.edu.chmnu.fks.oop.lombok;

import java.util.Objects;

public class FatData {
    private int count;
    private String str;

    public FatData() {
    }

    public FatData(int count, String str) {
        this.count = count;
        this.str = str;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "FatData{" +
                "count=" + count +
                ", str='" + str + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FatData fatData = (FatData) o;
        return count == fatData.count &&
                Objects.equals(str, fatData.str);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, str);
    }
}
