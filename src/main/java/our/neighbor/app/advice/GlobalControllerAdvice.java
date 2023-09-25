package our.neighbor.app.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import our.neighbor.app.controller.common.CustomErrorResponse;
import our.neighbor.app.exception.DuplicateException;

import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(DuplicateException.class)
    public CustomErrorResponse duplicateNicknameExceptionHandler(DuplicateException e) {
        return new CustomErrorResponse(SC_INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public CustomErrorResponse globalExceptionHandler(Exception e) {
        return new CustomErrorResponse(SC_INTERNAL_SERVER_ERROR, e.getMessage());
    }



}
