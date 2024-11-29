package lk.talentfort.health_information_system.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginResponseDTO {

    private String token;
}
