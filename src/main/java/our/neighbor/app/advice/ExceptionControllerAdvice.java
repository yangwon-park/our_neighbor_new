package our.neighbor.app.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import our.neighbor.app.controller.common.CustomErrorResponse;
import our.neighbor.app.exception.DuplicateNicknameException;

import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(DuplicateNicknameException.class)
    public CustomErrorResponse duplicateNicknameExceptionHandler(DuplicateNicknameException e) {
        return new CustomErrorResponse(SC_INTERNAL_SERVER_ERROR, e.getMessage());
    }




}
