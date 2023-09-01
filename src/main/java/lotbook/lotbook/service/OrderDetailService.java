package lotbook.lotbook.service;

import lotbook.lotbook.dto.entity.OrderDetail;
import org.springframework.stereotype.Service;

@Service
public interface OrderDetailService {
    int insert(OrderDetail o) throws Exception;
}
