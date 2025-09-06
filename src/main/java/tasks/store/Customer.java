package tasks.store;

import java.time.LocalDateTime;

public class Customer {
    private String customerId;
    private String name;
    private String email;
    private LocalDateTime registeredAt;
    private int age;
    private String city;

    public Customer(String customerId, String name, String email, LocalDateTime registeredAt, int age, String city) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.registeredAt = registeredAt;
        this.age = age;
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public int getAge() {
        return age;
    }
}
