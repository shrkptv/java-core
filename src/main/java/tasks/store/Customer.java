package tasks.store;

import java.time.LocalDateTime;

public class Customer {
    private String customerId;
    private String name;
    private String email;
    private LocalDateTime registeredAt;
    private int age;
    private String city;

    public String getCity() {
        return city;
    }

    public String getCustomerId() {
        return customerId;
    }
}
