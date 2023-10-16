package product.star.spring.basics.homework.model;

import java.time.Duration;
import java.util.Objects;

public final class Result {
    private final Person person;
    private final Distance distance;
    private final Duration time;

    public Result(Person person, Distance distance, Duration time) {
        this.person = person;
        this.distance = distance;
        this.time = time;
    }

    public boolean hasGender(Gender gender) {
        return person.gender() == gender;
    }

    public boolean hasDistance(Distance distance) {
        return this.distance == distance;
    }

    public Person person() {
        return person;
    }

    public Distance distance() {
        return distance;
    }

    public Duration time() {
        return time;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Result) obj;
        return Objects.equals(this.person, that.person) &&
                Objects.equals(this.distance, that.distance) &&
                Objects.equals(this.time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person, distance, time);
    }

    @Override
    public String toString() {
        return "Result[" +
                "person=" + person + ", " +
                "distance=" + distance + ", " +
                "time=" + time + ']';
    }

}