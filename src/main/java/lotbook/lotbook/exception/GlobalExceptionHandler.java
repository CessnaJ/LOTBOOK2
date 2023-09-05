package lotbook.lotbook.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ModelAndView handleCustomException(CustomException ex) {

        ModelAndView modelAndView = new ModelAndView("index");

        modelAndView.addObject("center", "error");
        modelAndView.addObject("errorCode", ex.getErrorCode().getCode());
        modelAndView.addObject("errorMessage", ex.getErrorCode().getMessage());

        return modelAndView;

    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleNotFoundException(NoHandlerFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("404"); // 404페이지로 넘깁니다.
        return modelAndView;
    }


}