package org.mysterymuscle.randomgohomebooster.interceptor;


import org.mysterymuscle.randomgohomebooster.domain.Member;
import org.springframework.web.servlet.HandlerInterceptor;

public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler) throws Exception {
        // exception for login page
        if (request.getRequestURI().equals("/admin/login")) {
            return true;
        }

        Member currentUser = (Member) request.getSession().getAttribute("currentUser");

        if (currentUser == null || currentUser.isAdmin() == false) {
            response.sendRedirect("/admin/login");
            return false;
        }
        return true;
    }
}
