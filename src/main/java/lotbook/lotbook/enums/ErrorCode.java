package lotbook.lotbook.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    ERROR_CODE_1("E1001", "에러메세지를 이렇게 작성해주시면 됩니다."),

    PRODUCT_DETAIL_ERROR_1("E2001", "해당 상품정보가 없습니다."),
    PRODUCT_DETAIL_ERROR_2("E2002", "상품정보를 받아오는 도중 문제가 생겼습니다."),

    CART_ERROR_1("E8000", "장바구니 에러"),
    CART_ERROR_2("E8001", "장바구니 에러 - 재고 부족"),

    ORDER_ERROR_1("E3001", "주문 에러 - 주문 실패"),
    ORDER_ERROR_2("E3002", "주문 에러 - 주문 조회 실패"),
    ORDER_ERROR_3("E3003", "주문 에러 - 주문 상세 삽입 실패"),
    ORDER_ERROR_4("E3004", "주문 에러 - 주문 상세 수정 실패"),
    ORDER_ERROR_5("E3005", "주문 에러 - 주문 상세 조회(단건) 실패"),
    ORDER_ERROR_6("E3006", "주문 에러 - 주문 상세 조회 실패"),
    ORDER_ERROR_7("E3007", "주문 에러 - 주문 전체 조회 실패");


    private final String code;
    private final String message;

}
