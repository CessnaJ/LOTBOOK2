package lotbook.lotbook.repository;

import lotbook.lotbook.dto.entity.Order;

import java.util.List;

public interface OrderMapper {

    Order select(Order order);
    int insert(Order order);
    List<Order> selectall(Order order);
}
