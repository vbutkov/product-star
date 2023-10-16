package product.star.spring.basics.homework.model;

import java.util.Objects;

public final class Person {
    private final String name;
    private final Gender gender;

    public Person(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    public String name() {
        return name;
    }

    public Gender gender() {
        return gender;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Person) obj;
        return Objects.equals(this.name, that.name) &&
                Objects.equals(this.gender, that.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gender);
    }

    @Override
    public String toString() {
        return "Person[" +
                "name=" + name + ", " +
                "gender=" + gender + ']';
    }

}
