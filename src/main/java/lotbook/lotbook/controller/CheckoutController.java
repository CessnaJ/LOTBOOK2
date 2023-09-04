package lotbook.lotbook.controller;

import lotbook.lotbook.dto.entity.*;
import lotbook.lotbook.dto.response.CartProduct;
import lotbook.lotbook.dto.response.OrderDetailResponse;
import lotbook.lotbook.enums.PointStateEnum;
import lotbook.lotbook.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("checkout")
public class CheckoutController {


    private final ProductService productService;

    private final OrderService orderService;
    private final OrderDetailService orderDetailService;

    private final CartService cartService;
    private final PointService pointService;
    private final MemberService memberService;

    @GetMapping(value = "/api/checkoutbuynow")
    public String CheckoutPage(Model model, @RequestParam long count, @RequestParam int productId, @RequestParam long memberSeq) {
        log.warn("제품 상세에서 바로 주문 페이지");


        model.addAttribute("center", "checkoutbuynow");

        List<CartProduct> productList = new ArrayList<>();
        try {
            Product res = productService.get(productId);
            productList.add(new CartProduct());
            productList.get(0).setName(res.getName());
            productList.get(0).setPrice(res.getPrice());
            productList.get(0).setDiscountRate(res.getDiscountRate());
            productList.get(0).setTotalPoint((int) (Math
                    .floor(res.getPrice() * count * res.getPointAccumulationRate() * 0.01)));
            productList.get(0).setCount((int) count);
            int priceMuldiscountRate = (int) ((int) ((int) res.getPrice() * ((100 - res.getDiscountRate()) * 0.01))
                    * count);

            productList.get(0).setTotalPrice(priceMuldiscountRate - priceMuldiscountRate % 10);
            model.addAttribute("res", res);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        model.addAttribute("orderProductList", productList);
        model.addAttribute("count", count);
        model.addAttribute("productId", productId);

        return "index";
    }

    @PostMapping("/api/checkout-result")
    public String CheckoutResultPage(Model model, @RequestParam long count, @RequestParam int price, @RequestParam double pointAccumulationRate, @RequestParam long productId, HttpServletRequest request) throws Exception {
        model.addAttribute("center", "checkout-result");
        HttpSession session = request.getSession();
        Member loggedInUser = (Member) session.getAttribute("logincust");
        long memberSeq = loggedInUser.getSequence();


        String receiver_name = request.getParameter("input__receiverName");
        String order_phone = request.getParameter("input__phone");
        String zipcode = request.getParameter("input__zipcode");
        String street_address = request.getParameter("input__street_address");
        String address_detail = request.getParameter("input__address_detail");
        String vendor_message = request.getParameter("input__vendor_message");
        String email = request.getParameter("input__email");


        Order order = Order.builder().receiverName(receiver_name).orderPhone(order_phone)
                .vendorMessage(vendor_message).addressDetail(address_detail).streetAddress(street_address)
                .receiverEmail(email).zipcode(zipcode).memberSequence(Long.parseLong(String.valueOf(loggedInUser.getSequence()))).build();

        int usePoint = Integer.parseInt(request.getParameter("usePoint")) * -1;

        int totalPoint = 0;
        int totalPrice = 0;
            // 바로 구매한 경우
            try {
                orderService.register(order);

                List<Order> orderList = orderService
                        .getAll(Order.builder().memberSequence(loggedInUser.getSequence()).build());

                OrderDetail orderDetail = OrderDetail.builder().orderSequence(orderList.get(0).getSequence())
                        .count((int) count).productPoint(pointAccumulationRate * 0.01 * count * price).productPrice(price)
                        .productSequence(productId).build();


                orderDetailService.register(orderDetail);


            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            try {
                List<Order> orderList = orderService
                        .getAll(Order.builder().memberSequence(Long.parseLong(String.valueOf(memberSeq))).build());
                request.setAttribute("orderResult", orderList.get(0)); // 방금 생성된 Order

                List<OrderDetailResponse> orderDetail = new ArrayList<>();
                orderDetail = orderDetailService.get(orderList.get(0).getSequence());

                for (int j = 0; j < orderDetail.size(); j++) {
                    Product product = productService.get((int) orderDetail.get(j).getProductSequence());
                    orderDetail.get(j).setOrderDetailProduct(productService.get((int) orderDetail.get(j).getProductSequence()));


                    totalPrice += orderDetail.get(j).getProductPrice() * ( 1 - product.getDiscountRate() * 0.01) * count;
                    totalPoint += orderDetail.get(j).getProductPoint() * count;
                }


                // 방금 생성된 orderDetail List
                request.setAttribute("orderDetailResult", orderDetail);
                request.setAttribute("totalPrice", totalPrice - totalPrice % 10);
                request.setAttribute("totalPoint", totalPoint);
                request.setAttribute("usedPoint", usePoint);

                // 포인트 사용
                Point point = Point.builder().point(usePoint).state(PointStateEnum.USED).memberSequence(Long.parseLong(String.valueOf(memberSeq))).build();
                pointService.register(point);
                pointService.modify(point);


                Member updatedUserInfo = memberService.get(Member.builder().email(loggedInUser.getEmail()).build());
                session.setAttribute("logincust", updatedUserInfo);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

//        }
        return "index";
    }
}