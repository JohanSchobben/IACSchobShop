package iac.schobshop.Schobshop.exceptions.handler;

import iac.schobshop.Schobshop.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ObjectNotFoundExceptionHandler {

    @ResponseStatus(code= HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = ObjectNotFoundException.class)
    public ModelAndView handleException(Exception exception){
        System.out.println("in handler");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/404");
        modelAndView.addObject("exception",exception);
        return modelAndView;
    }
}
