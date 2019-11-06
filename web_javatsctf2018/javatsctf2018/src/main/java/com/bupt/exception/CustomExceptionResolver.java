package com.bupt.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomExceptionResolver implements HandlerExceptionResolver {
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

        ModelAndView modelAndView = new ModelAndView();

        CustomException customException;

        String message = "exception";
        System.out.println("com.bupt.exception.CustomExceptionResolver");

        modelAndView.addObject("message", message);
        modelAndView.setViewName("error");

        return modelAndView;
    }
}
