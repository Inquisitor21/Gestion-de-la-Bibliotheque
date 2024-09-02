package tp4.presentation;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import tp4.presentation.exception.CopiesEpuiseesException;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    
    @ExceptionHandler(CopiesEpuiseesException.class)
    public ModelAndView handleCopiesEpuiseesException(CopiesEpuiseesException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }
}