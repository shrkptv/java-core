package tasks.store.service;

import tasks.store.*;

import java.util.*;
import java.util.stream.Collectors;

public class OrderService {

    public List<String> getUniqueCities(List<Order> orders)
    {
        return orders.stream()
                .map(order -> order.getCustomer().getCity())
                .distinct()
                .toList();
    }

    public double getTotalIncome(List<Order> orders)
    {
        return orders.stream()
                .filter(order -> order.getStatus() == OrderStatus.DELIVERED)
                .flatMap(order -> order.getItems().stream())
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }

    public String getPopularProduct(List<Order> orders)
    {
        Map<String, Integer> products = orders.stream()
                        .flatMap(order -> order.getItems().stream())
                        .collect(Collectors.groupingBy(OrderItem::getProductName,
                                Collectors.summingInt(OrderItem::getQuantity)));

        return products.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    public double getAverageCheck(List<Order> orders)
    {
        return orders.stream()
                .filter(order -> order.getStatus() == OrderStatus.DELIVERED)
                .mapToDouble(order -> order.getItems().stream()
                        .mapToDouble(item -> item.getPrice()*item.getQuantity())
                        .sum())
                .average()
                .orElse(0.0);
    }

    public Map<String, Integer> getFrequentCustomers(List<Order> orders)
    {
        Map<String, Integer> customers = orders.stream()
                .collect(Collectors.groupingBy(order -> order.getCustomer().getCustomerId(),
                        Collectors.summingInt(order -> 1)));

        return customers.entrySet().stream()
                .filter(entry -> entry.getValue() > 5)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
