package lotbook.lotbook.service;

import lombok.RequiredArgsConstructor;
import lotbook.lotbook.dto.entity.OrderDetail;
import lotbook.lotbook.dto.response.OrderDetailResponse;
import lotbook.lotbook.exception.CustomException;
import lotbook.lotbook.repository.OrderDetailMapper;
import lotbook.lotbook.repository.ProductMapper;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;

import static lotbook.lotbook.enums.ErrorCode.*;


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
                throw new CustomException(ORDER_ERROR_2);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(ORDER_ERROR_3);
        }
        return result;
    }

    @Override
    public List<OrderDetailResponse> get(long orderId) throws Exception {
        List<OrderDetailResponse> orderDetail;
        try {
            orderDetail = orderDetailMapper.selectAll(orderId);
        } catch(Exception e) {
            e.printStackTrace();
            throw new CustomException(ORDER_ERROR_2);
        }
        return orderDetail;
    }

    @Override
    public OrderDetail get(OrderDetail v) {
        OrderDetail orderDetail = null;

        try {
            orderDetail = orderDetailMapper.selectOne(v);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(ORDER_ERROR_2);
        }

        return orderDetail;
    }

    @Override
    public int modify(OrderDetail v) {
        int result = 0;

        try {
            result = orderDetailMapper.modify(v);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(ORDER_ERROR_1);
        }
        return result;
    }
}
