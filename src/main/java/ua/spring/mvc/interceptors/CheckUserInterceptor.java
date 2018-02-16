package ua.spring.mvc.interceptors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import ua.spring.mvc.dao.impls.SQLiteDAO;
import ua.spring.mvc.dao.objects.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckUserInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (request.getRequestURI().contains("check-user")) {

            User user = modelAndView.getModel().get("user");
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            SQLiteDAO sqLiteDAO = (SQLiteDAO) context.getBean("SQLiteDAO");
            if(!sqLiteDAO.isUserRegistered(user.getTel_number(), user.getPasswor())) {

            }
        }
    }
}
