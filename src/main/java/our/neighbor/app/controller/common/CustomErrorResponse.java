package our.neighbor.app.controller.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomErrorResponse {

    private int status;

    private String message;
}
