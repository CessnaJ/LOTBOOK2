package lotbook.lotbook.service;

import lotbook.lotbook.dto.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    int register(Order order) throws Exception;
    Order get(Order order) throws Exception;
    List<Order> getAll(Order order) throws Exception;
}
