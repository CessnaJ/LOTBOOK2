package lotbook.lotbook.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupDto {
    private String email;
    private String password;
    private String name;
    private String phone;
    private String zipcode;
    private String street_address_1;
    private String street_address_2;
    private String address_detail;
}
