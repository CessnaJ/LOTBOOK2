package lotbook.lotbook.service;

import lotbook.lotbook.dto.entity.OrderDetail;
import lotbook.lotbook.dto.response.OrderDetailResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderDetailService {
    int register(OrderDetail o) throws Exception;
    List<OrderDetailResponse> get(long orderId) throws Exception;
}

