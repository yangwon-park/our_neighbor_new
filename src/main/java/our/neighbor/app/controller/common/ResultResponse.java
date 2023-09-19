package our.neighbor.app.controller.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultResponse<T> {

    private int count;
    private T data;

    public ResultResponse(T data) {
        this.data = data;
    }
}
