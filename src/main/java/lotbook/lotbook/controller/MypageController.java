package lotbook.lotbook.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotbook.lotbook.dto.entity.*;
import lotbook.lotbook.dto.response.CartProduct;
import lotbook.lotbook.dto.response.OrderDetailResponse;
import lotbook.lotbook.dto.response.ReviewDetails;
import lotbook.lotbook.enums.PointStateEnum;
import lotbook.lotbook.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {

    private final CartService cartService;
    private final OrderService orderService;
    private final OrderDetailService orderDetailService;
    private final ProductService productService;
    private final PointService pointService;
    private final MemberService memberService;
    private final ReviewService reviewService;

    @GetMapping
    public String myPage(Model model, @RequestParam long memberSeq, HttpServletRequest request) {
        model.addAttribute("center", "mypage");
        HttpSession session = request.getSession();

        List<Cart> cartList = new ArrayList<>();
        List<CartProduct> productList = new ArrayList<>();
        model.addAttribute("myCartList", null);
        model.addAttribute("myCartProductList", null);

        Cart cart = Cart.builder().memberSequence(memberSeq).build();

        try {
            cartList = cartService.getAll(cart);
            model.addAttribute("myCartList", cartList);
            productList = cartService.getProductInfo(cart);
            model.addAttribute("myCartProductList", productList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Order> orderList = new ArrayList<>();
        List<ReviewDetails> reviewDetailList = null;

        Order order = Order.builder().memberSequence(memberSeq).build();
        Member memberInfo = Member.builder().sequence(memberSeq).build();

        try {
            orderList = orderService.getAll(order); // 1. user sequence에 해당하는 order 내역 전체 조회
            reviewDetailList = reviewService.get(memberSeq);

            // 2. order sequence에 해당하는 orderDetail 채워주기
            for (int i = 0; i < orderList.size(); i++) {
                List<OrderDetailResponse> orderDetail = new ArrayList<>();

                orderDetail = orderDetailService.get(orderList.get(i).getSequence());

                // 3. orderDetail 각각에 해당하는 Product, Review 작성여부 채워주기
                for (int j = 0; j < orderDetail.size(); j++) {
                    orderDetail.get(j).setOrderDetailProduct(productService.get((int) orderDetail.get(j).getProductSequence()));
                    // 리뷰 기 작성여부 채워주기
                    Review reviewInfo = Review.builder().orderdetailSequence(orderDetail.get(j).getSequence()).build();
                    Review result = reviewService.get(reviewInfo);
                    if (result == null) {
                        orderDetail.get(j).setReviewState("NONEXIST");
                    } else {
                        orderDetail.get(j).setReviewState("EXIST");
                    }
                }

                orderList.get(i).setOrderDetailList(orderDetail);

            }
            // 2-2. review의 product_sequence에 해당하는 Product 정보 채워주기
            for(int i=0; i< reviewDetailList.size(); i++) {
                reviewDetailList.get(i).setReviewDetailProduct(productService.get((int) reviewDetailList.get(i).getProductSequence()));

            }

            // 3. myPage로 보내기
            model.addAttribute("myOrderList", orderList);
            model.addAttribute("myReviewList", reviewDetailList);

            int cartCount = cartService.getCartCount(memberSeq);
            model.addAttribute("cartCount", cartCount);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "index";
    }

    @GetMapping("/changeOrderState")
    public String changeOrderState(Model model, @RequestParam String state, @RequestParam long sequence, @RequestParam long productSequence, @RequestParam int count, HttpServletRequest request) {
        model.addAttribute("center", "mypage");
        HttpSession session = request.getSession();
        Member loggedInUser = (Member) session.getAttribute("logincust");
        long memberSeq = loggedInUser.getSequence();
        List<Cart> cartList = new ArrayList<>();
        List<CartProduct> productList = new ArrayList<>();
        List<Order> orderList = new ArrayList<>();
        List<ReviewDetails> reviewDetailList = new ArrayList<>();

        OrderDetail orderDetail = OrderDetail.builder().state(state).sequence(sequence).productSequence(productSequence).count(count).build();
        Cart cart = Cart.builder().memberSequence(memberSeq).build();
        Order order = Order.builder().memberSequence(memberSeq).build();

        try {
            orderDetailService.modify(orderDetail);
            productService.updateByProductKeyWithOrderDetail(orderDetail);
            reviewDetailList = reviewService.get(memberSeq);

            if (state.equals("CONFIRMED")) {
                Product productInfo = productService.get((int) productSequence);

                int totalPoint = (int) Math.floor(productInfo.getPrice() * count * productInfo.getPointAccumulationRate() * 0.01);

                Point accumulatedPoint = Point.builder().point(totalPoint).state(PointStateEnum.ACCUMULATED).memberSequence(memberSeq).build();
                pointService.register(accumulatedPoint);
                memberService.updatePoint(memberSeq);

                Member memberInfo = memberService.getById(memberSeq);
                session.setAttribute("logincust", memberInfo);
            }

            cartList = cartService.getAll(cart);
            model.addAttribute("myCartList", cartList);
            productList = cartService.getProductInfo(cart);
            model.addAttribute("myCartProductList", productList);
            orderList = orderService.getAll(order);

            for (int i = 0; i < orderList.size(); i++) {
                List<OrderDetailResponse> orderDetailList = new ArrayList<>();

                orderDetailList = orderDetailService.get(orderList.get(i).getSequence());

                for (int j = 0; j < orderDetailList.size(); j++) {
                    orderDetailList.get(j).setOrderDetailProduct(productService.get((int) orderDetailList.get(j).getProductSequence()));
                    Review reviewInfo = Review.builder().orderdetailSequence(orderDetailList.get(j).getSequence()).build();

                    Review result = reviewService.get(reviewInfo);

                    if (result == null) {
                        orderDetailList.get(j).setReviewState("NONEXIST");
                    } else {
                        orderDetailList.get(j).setReviewState("EXIST");
                    }
                }
                orderList.get(i).setOrderDetailList(orderDetailList);
            }

            for(int i=0; i< reviewDetailList.size(); i++) {
                reviewDetailList.get(i).setReviewDetailProduct(productService.get((int) reviewDetailList.get(i).getProductSequence()));
            }

            model.addAttribute("myOrderList", orderList);
            model.addAttribute("myReviewList", reviewDetailList);

            int cartCount = cartService.getCartCount(memberSeq);
            request.setAttribute("cartCount", cartCount);

        } catch (Exception e) {
            e.printStackTrace();
        }



        return "index";
    }
}
