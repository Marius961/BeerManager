package ua.spring.mvc.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import ua.spring.mvc.objects.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckUserInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (request.getRequestURI().contains("check-user")) {

            User user = (User) modelAndView.getModel().get("user");
            if (user.getName().equals("admin") || user.getName().equals("")) {
                response.sendRedirect(request.getContextPath() + "/failed");
            }

        }
    }
}
