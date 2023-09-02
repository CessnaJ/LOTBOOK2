package lotbook.lotbook.service;

import lombok.RequiredArgsConstructor;
import lotbook.lotbook.dto.entity.Order;
import lotbook.lotbook.repository.OrderMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;

    @Override
    public Order get(Order order) throws Exception {
        Order resOrder = null;

        try {
            resOrder = orderMapper.select(order);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("ER3002");
        }

        return resOrder;
    }

    @Override
    public List<Order> getAll(Order order) throws Exception {
        List<Order> orders = new ArrayList<>();
        try {
            orders = orderMapper.selectall(order);
        } catch(Exception e) {
            e.printStackTrace();
            throw new Exception("추후 에러코드 추가 - order 전체 조회 에러");
        }
        return orders;
    }

    @Override
    public int register(Order order) throws Exception {
        int result = 0;

        try {
            result = orderMapper.insert(order);
        } catch (Exception e) {
            e.printStackTrace();

            throw new Exception("ER3001");
        }

        return result;
    }
}
