package lotbook.lotbook.service;

import lotbook.lotbook.dto.entity.Order;

import java.util.List;

public interface OrderService {
    int register(Order order) throws Exception;
    Order get(Order order) throws Exception;
    List<Order> getAll(Order order) throws Exception;
}
