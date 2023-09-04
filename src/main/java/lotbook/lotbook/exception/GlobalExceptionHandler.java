package lotbook.lotbook.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ModelAndView handleCustomException(CustomException ex) {

        ModelAndView modelAndView = new ModelAndView("index"); // error는 JSP 파일명

        modelAndView.addObject("center", "error");
        modelAndView.addObject("errorCode", ex.getErrorCode().getCode());
        modelAndView.addObject("errorMessage", ex.getErrorCode().getMessage());

        return modelAndView;

    }
}