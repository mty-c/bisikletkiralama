package LoginFilter;

import Entity.Kullanici;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dazlakgandalf
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String url = req.getRequestURI();

        Kullanici u = (Kullanici) req.getSession().getAttribute("user");

        if (u == null) {
            if (url.contains("adminbisiklet") || url.contains("adminatv") || url.contains("adminkullanici")) {
                res.sendRedirect(req.getContextPath() + "/Login.xhtml");
            } else if (url.contains("atv") || url.contains("bisiklet") || url.contains("motor")) {
                res.sendRedirect(req.getContextPath() + "/Login.xhtml");
            } else {
                chain.doFilter(request, response);
            }

        } else {
            if (u.getRole() == 1) {
                if (url.contains("Login")) {
                    res.sendRedirect(req.getContextPath() + "/index.xhtml");
                } else if (url.contains("logout")) {
                    req.getSession().invalidate();
                    res.sendRedirect(req.getContextPath() + "/index.xhtml");

                } else {
                    chain.doFilter(request, response);
                }
            } else {
                if (url.contains("adminbisiklet") || url.contains("adminatv") || url.contains("adminkullanici")) {
                    res.sendRedirect(req.getContextPath() + "/index.xhtml");
                } else if (url.contains("logout")) {
                    req.getSession().invalidate();
                    res.sendRedirect(req.getContextPath() + "/index.xhtml");
                } else {
                    chain.doFilter(request, response);
                }
            }
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

}
