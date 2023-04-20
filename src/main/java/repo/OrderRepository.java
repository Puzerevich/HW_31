package repo;

import entitys.Order;
import entitys.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class OrderRepository {
    private final List<Order> ORDERS;

    public OrderRepository() {
        ORDERS = new ArrayList<>();
        Product cola = new Product(1L, "Water", new BigDecimal(2));
        Product beer = new Product(2L, "Juice", new BigDecimal(5));
        Product water = new Product(3L, "Fanta", new BigDecimal(2));
        List<Product> p = Arrays.asList(cola, beer, water);
        BigDecimal sump = sumCost(p);
        Product salad = new Product(1L, "Double burger", new BigDecimal(10));
        Product chicken = new Product(2L, "Chicken Roll", new BigDecimal(15));
        Product bigmac = new Product(3L, "Big Mac", new BigDecimal(20));
        List<Product> p2 = Arrays.asList(salad, bigmac, chicken);
        BigDecimal sump2 = sumCost(p2);
        ORDERS.add(new Order(1L, LocalDateTime.now(), sump, p));
        ORDERS.add(new Order(2L, LocalDateTime.now(), sump2, p2));
    }

    private BigDecimal sumCost(List<Product> products) {
        return products.stream()
                .map(Product::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Order getOrderById(long id) {
        return ORDERS.stream()
                .filter(order -> order.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Order> getAllOrders() {
        return ORDERS;
    }

    public void addOrder(Order order) {
        ORDERS.add(order);
    }
}
