package com.tama.javafxtest.model;

import javafx.beans.property.*;
import java.time.LocalDate;

public class Customer {
    private final IntegerProperty idx;
    private final StringProperty nik;
    private final StringProperty name;
    private final ObjectProperty<LocalDate> born;
    private final BooleanProperty active;
    private final IntegerProperty salary;

    public Customer() {
        this.idx = new SimpleIntegerProperty();
        this.nik = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
        this.born = new SimpleObjectProperty<>();
        this.active = new SimpleBooleanProperty();
        this.salary = new SimpleIntegerProperty();
    }

    public Customer(int idx, String nik, String name, LocalDate born, boolean active, int salary) {
        this();
        setIdx(idx);
        setNik(nik);
        setName(name);
        setBorn(born);
        setActive(active);
        setSalary(salary);
    }

    // Getters and Setters
    public int getIdx() { return idx.get(); }
    public void setIdx(int idx) { this.idx.set(idx); }
    public IntegerProperty idxProperty() { return idx; }

    public String getNik() { return nik.get(); }
    public void setNik(String nik) { this.nik.set(nik); }
    public StringProperty nikProperty() { return nik; }

    public String getName() { return name.get(); }
    public void setName(String name) { this.name.set(name); }
    public StringProperty nameProperty() { return name; }

    public LocalDate getBorn() { return born.get(); }
    public void setBorn(LocalDate born) { this.born.set(born); }
    public ObjectProperty<LocalDate> bornProperty() { return born; }

    public boolean isActive() { return active.get(); }
    public void setActive(boolean active) { this.active.set(active); }
    public BooleanProperty activeProperty() { return active; }

    public int getSalary() { return salary.get(); }
    public void setSalary(int salary) { this.salary.set(salary); }
    public IntegerProperty salaryProperty() { return salary; }

    @Override
    public String toString() {
        return String.format("Customer{idx=%d, nik='%s', name='%s', born=%s, active=%s, salary=%d}",
                getIdx(), getNik(), getName(), getBorn(), isActive(), getSalary());
    }
}