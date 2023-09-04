package lotbook.lotbook.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    ERROR_CODE_1("E001", "에러메세지를 이렇게 작성해주시면 됩니다."),

    PRODUCT_DETAIL_ERROR_1("E2001", "해당 상품정보가 없습니다."),

    CART_ERROR_1("E8000", "장바구니 에러"),

    CART_ERROR_2("E8001", "장바구니 에러 - 재고 부족");




    private final String code;
    private final String message;

}
