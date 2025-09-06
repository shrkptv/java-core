import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.store.*;
import tasks.store.service.OrderService;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class OrderServiceTest {

    private OrderService orderService;
    private List<Order> orders;

    @BeforeEach
    public void fillInList()
    {
        orderService = new OrderService();

        Customer customer1 = new Customer("c1", "Alexander", "alexander@gmail.com",
                LocalDateTime.of(2025, 5, 25, 9, 0), 19, "Borisov");
        Customer customer2 = new Customer("c2", "Ryhor", "ryhor@gmail.com",
                LocalDateTime.of(2025, 3, 10, 18, 17), 24, "Minsk");
        Customer customer3 = new Customer("c3", "Denis", "denis@gmail.com",
                LocalDateTime.of(2025, 4, 14, 11, 0), 20, "Grodno");

        OrderItem orderItem1 = new OrderItem("Phone", 2, 1200, Category.ELECTRONICS);
        OrderItem orderItem2 = new OrderItem("Book", 2, 18, Category.BOOKS);
        OrderItem orderItem3 = new OrderItem("Train", 1, 100, Category.TOYS);
        OrderItem orderItem4 = new OrderItem("Laptop", 1, 3000, Category.ELECTRONICS);

        Order order1 = new Order("o1", LocalDateTime.of(2025, 6, 5, 13, 0),
                customer1, List.of(orderItem1, orderItem2), OrderStatus.DELIVERED);
        Order order2 = new Order("o2", LocalDateTime.of(2025, 4, 21, 17, 0),
                customer2, List.of(orderItem4), OrderStatus.DELIVERED);
        Order order3 = new Order("o3", LocalDateTime.of(2025, 9, 4, 12, 0),
                customer3, List.of(orderItem3, orderItem2), OrderStatus.NEW);

        orders = List.of(order1, order2, order3);
    }

    @Test
    public void testGetUniqueCities()
    {
        List<String> cities = orderService.getUniqueCities(orders);

        assertEquals(3, cities.size());
        assertTrue(cities.contains("Minsk"));
        assertTrue(cities.contains("Borisov"));
        assertTrue(cities.contains("Grodno"));
    }

    @Test
    public void testGetTotalIncome()
    {
        //order1 + order2 = (2*1200+2*18)+3000 = 5436
        assertEquals(5436, orderService.getTotalIncome(orders));
    }

    @Test
    public void testGetPopularProduct()
    {
        String popularProduct = orderService.getPopularProduct(orders);

        assertEquals("Book", popularProduct);
        assertNotNull(popularProduct);
    }

    @Test
    public void testGetAverageCheck()
    {
        // (order1+order2)/2 = 5436/2 = 2718
        assertEquals(2718, orderService.getAverageCheck(orders));
    }

    @Test
    public void testGetFrequentCustomers()
    {
        orders = new ArrayList<>(orders);
        for(int i = 4; i < 9; i++)
        {
            orders.add(
                    new Order("o" + i, LocalDateTime.now(), orders.get(0).getCustomer(),
                            List.of(), OrderStatus.NEW)
            );
        }

        Map<String, Integer> frequentCustomers = orderService.getFrequentCustomers(orders);
        assertTrue(frequentCustomers.containsKey("c1"));
        assertTrue(frequentCustomers.get("c1") > 5);
    }

}
