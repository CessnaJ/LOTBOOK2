package lotbook.lotbook.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    ERROR_CODE_1("E001", "에러메세지를 이렇게 작성해주시면 됩니다."),

    ERROR_CODE_2("E002", "에러메세지를 이렇게 작성해주세요.");




    private final String code;
    private final String message;

}
