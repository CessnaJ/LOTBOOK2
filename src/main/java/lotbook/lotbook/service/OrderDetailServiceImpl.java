package lotbook.lotbook.service;

import lombok.RequiredArgsConstructor;
import lotbook.lotbook.dto.entity.OrderDetail;
import lotbook.lotbook.dto.response.OrderDetailResponse;
import lotbook.lotbook.repository.OrderDetailMapper;
import lotbook.lotbook.repository.ProductMapper;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;


@Slf4j
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

    @Override
    public List<OrderDetailResponse> get(long orderId) throws Exception {
//        System.out.println("Mapper에서 orderId- " + orderId);
        log.info("Mapper에서 orderDetail"+  orderId);
        List<OrderDetailResponse> orderDetail;
        try {
            orderDetail = orderDetailMapper.selectAll(orderId);

            System.out.println("Mapper에서 - " + orderDetail);
        } catch(Exception e) {
            e.printStackTrace();
            throw new Exception("ER3006");
        }
        return orderDetail;
    }
}
