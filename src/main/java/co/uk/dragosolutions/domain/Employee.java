package co.uk.dragosolutions.domain;

import com.google.common.base.MoreObjects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String surname;
    private int age;
    private BigDecimal wage;

    public Employee() {
    }

    public Employee(String name, String surname, int age, BigDecimal wage) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.wage = wage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public BigDecimal getWage() {
        return wage;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("surname", surname)
                .add("age", age)
                .add("wage", wage)
                .toString();
    }
}
