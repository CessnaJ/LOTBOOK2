package lotbook.lotbook.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotbook.lotbook.dto.entity.*;
import lotbook.lotbook.dto.request.CartToOrderRequest;
import lotbook.lotbook.dto.response.CartProduct;
import lotbook.lotbook.enums.PointStateEnum;
import lotbook.lotbook.service.CartService;
import lotbook.lotbook.service.MemberService;
import lotbook.lotbook.service.OrderService;
import lotbook.lotbook.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;
    private final OrderService orderService;
    private final MemberService memberService;
    @GetMapping(value = "/api/shopingCart")
    public String ShopingCartPage(Model model, @RequestParam long memberSeq) {
        log.warn("장바구니 상세 페이지");
        model.addAttribute("center", "shoping-cart");

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

            int cartCount = cartService.getCartCount(memberSeq);
            model.addAttribute("cartCount", cartCount);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "index";
    }

    @GetMapping(value = "/api/deleteCart")
    public String DeleteCart(Model model, @RequestParam long sequence, @RequestParam long memberSequence, @RequestParam int isCart) {

        if (isCart == 0) {
            model.addAttribute("center", "mypage");
        } else {
            model.addAttribute("center", "shoping-cart");
        }

        List<Cart> cartList = new ArrayList<>();
        List<CartProduct> productList = new ArrayList<>();

        Cart cart = Cart.builder().sequence(sequence).memberSequence(memberSequence).build();

        try {
            cartService.remove(cart);
            cartList = cartService.getAll(cart);
            model.addAttribute("myCartList", cartList);

            productList = cartService.getProductInfo(cart);
            model.addAttribute("myCartProductList", productList);

            int cartCount = cartService.getCartCount(memberSequence);
            model.addAttribute("cartCount", cartCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

    @GetMapping(value = "/api/cartToOrder")
    public String CartToOrder(Model model, @RequestParam String sequences) {
        model.addAttribute("center", "checkout");

        model.addAttribute("sequences", sequences);
        String[] cartSequences = sequences.split(",");
        List<CartProduct> productList = new ArrayList<>();
        int totalPrice = 0;
        int totalPoint = 0;

        for (int i = 0; i < cartSequences.length; i++) {
            Cart cart = Cart.builder().sequence(Long.parseLong(cartSequences[i])).build();
            try {
                productList.add(cartService.cartGet(cart));

                if (i == 0) {
                    totalPrice = (int) ((productList.get(i).getPrice()
                            * ((100 - productList.get(i).getDiscountRate()) * 0.01)) * productList.get(i).getCount()
                            - ((productList.get(i).getPrice()
                            * ((100 - productList.get(i).getDiscountRate()) * 0.01))
                            * productList.get(i).getCount()) % 10);
                    totalPoint = (int) (Math.floor(productList.get(i).getPrice() * productList.get(i).getCount()
                            * productList.get(i).getPointAccumulationRate() * 0.01));
                } else {
                    totalPrice = (int) (productList.get(i - 1).getTotalPrice()
                            + (productList.get(i).getPrice()
                            * ((100 - productList.get(i).getDiscountRate()) * 0.01))
                            * productList.get(i).getCount()
                            - ((productList.get(i).getPrice()
                            * ((100 - productList.get(i).getDiscountRate()) * 0.01))
                            * productList.get(i).getCount()) % 10);
                    totalPoint = (int) (productList.get(i - 1).getTotalPoint()
                            + Math.floor(productList.get(i).getPrice() * productList.get(i).getCount()
                            * productList.get(i).getPointAccumulationRate() * 0.01));
                }

                productList.get(i).setTotalPrice(totalPrice);
                productList.get(i).setTotalPoint(totalPoint);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        model.addAttribute("orderProductList", productList);
        return "index";
    }

    @PostMapping(value = "/api/caartToOrderResult")
    public String CartToOrderResult(Model model, CartToOrderRequest cartToOrder, HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        Member loggedInUser = (Member) session.getAttribute("logincust");
//
//        model.addAttribute("center", "checkout-result");
//
//
//        Order order = Order.builder().receiverName(cartToOrder.getReceiverName()).orderPhone(cartToOrder.getOrderPhone())
//                .vendorMessage(cartToOrder.getVendorMessage()).addressDetail(cartToOrder.getAddressDetail()).streetAddress(cartToOrder.getStreetAddress())
//                .receiverEmail(cartToOrder.getEmail()).zipcode(cartToOrder.getZipcode()).memberSequence(cartToOrder.getMemberSequence()).build();
//
//        int totalPoint = 0;
//        int totalPrice = 0;
//        String[] cartSequences = cartToOrder.getSequences().split(",");
//
//        try {
//            orderService.insert(order);
//
//            // memberId에 해당하는 가장 최근 order sequence
//            List<Order> orderList = orderService
//                    .getAll(Order.builder().memberSequence(cartToOrder.getMemberSequence()).build());
//            List<OrderDetail> orderDetailList = new ArrayList<>();
//
//            // TODO : Order sequence에 해당하는 OrderDetail 채우기
//            for (int i = 0; i < cartSequences.length; i++) {
//                Cart tempCart = Cart.builder().sequence(Long.parseLong(cartSequences[i])).build();
//                Cart cart = cartService.get(tempCart);
//                Product tempProduct = Product.builder().sequence(cart.getProductSequence()).build();
//                Product product = productService.get(tempProduct);
//
//                try {
//
//                    OrderDetail orderDetail = OrderDetail.builder()
//                            .orderSequence(orderList.get(0).getSequence()).orderDetailProduct(product)
//                            .count(cart.getCount())
//                            .productPoint(product.getPointAccumulationRate() * 0.01 * product.getPrice())
//                            .productPrice(product.getPrice()).productSequence(product.getSequence()).build();
//                    totalPoint += product.getPointAccumulationRate() * 0.01 * cart.getCount()
//                            * product.getPrice();
//                    totalPrice += product.getPrice() * cart.getCount();
//                    orderDetailService.register(orderDetail);
//                    orderDetailList.add(orderDetail);
//
//                    cartService.remove(tempCart);
//                    int cartCount = cartService.getCartCount(cartToOrder.getMemberSequence());
//                    request.setAttribute("cartCount", cartCount);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//            request.setAttribute("orderResult", order);
//            request.setAttribute("orderDetailResult", orderDetailList);
//            request.setAttribute("totalPoint", totalPoint);
//            request.setAttribute("totalPrice", totalPrice);
//            request.setAttribute("usedPoint", cartToOrder.getUsePoint());
//
//            // 포인트 사용
//            Point point = Point.builder().point(cartToOrder.getUsePoint()).state(PointStateEnum.USED).memberSequence(cartToOrder.getMemberSequence()).build();
//            pointService.register(point);
//            pointService.modify(point);
//
//
//
//            Member updatedUserInfo = memberService.get(Member.builder().email(loggedInUser.getEmail()).build());
//            session.setAttribute("logincust", updatedUserInfo);
//
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        }
        return "index";
    }

}
