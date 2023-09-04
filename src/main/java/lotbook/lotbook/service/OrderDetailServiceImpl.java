package lotbook.lotbook.service;

import lombok.RequiredArgsConstructor;
import lotbook.lotbook.dto.entity.OrderDetail;
import lotbook.lotbook.repository.OrderDetailMapper;
import lotbook.lotbook.repository.ProductMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailMapper orderDetailMapper;
    private final ProductMapper productMapper;
    @Override
    public int register(OrderDetail v) throws Exception {
        int result = 0;
        try {
            result = orderDetailMapper.insert(v);
            if(result == 1) {
                // 성공
                productMapper.updateByProductKeyWithSalesCount(v);

            } else {
                throw new Exception("ER3002");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("ER3003");
        }
        return result;
    }
}
