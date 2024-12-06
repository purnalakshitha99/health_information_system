package lk.talentfort.health_information_system.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
public class MessageResponse {

    private String message;
    private UserResponse userResponse;
}
