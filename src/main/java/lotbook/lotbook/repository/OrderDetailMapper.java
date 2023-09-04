package lotbook.lotbook.repository;

import lotbook.lotbook.dto.entity.OrderDetail;
import lotbook.lotbook.dto.response.OrderDetailResponse;

import java.util.List;

public interface OrderDetailMapper {
    int insert(OrderDetail v);
    List<OrderDetailResponse> selectAll(long orderId);
}
