package com.pdh.apps.filter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mmonge
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"},
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ERROR})
public class AuthorizationFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(AuthorizationFilter.class.getName());

    // URI de la página de login
    private static final String LOGIN_URI = "/login.xhtml";

    // URI Regex para hacer Ignore
    private static final String IGNORE_URI_REGEX = new StringBuilder("(")
            .append("^").append(LOGIN_URI).append("$") // Login URI
            .append("|")
            .append("^/javax.faces.resource/.*$") // JSF Resource URI
            .append("|")
            .append("^/404.xhtml") // Error Pages URI
            .append("|")
            .append("^/error.xhtml") // Error Pages URI
            .append("|")
            .append("^/forgot-password.xhtml") // Error Pages URI
            .append("|")
            .append("^/forgot-Password-Code.xhtml") // Error Pages URI
            .append("|")
            .append("^/password-changes.xhtml") // Error Pages URI
            .append("|")
            .append("^/validateEmail.xhtml") // Email validation page URI
            .append(")").toString();

    // User Session Attribute Name
    private static final String USER_SESSION_ATTRIBUTE_NAME = "usr";

    static {
        LOG.log(Level.INFO, "IGNORE_URI_REGEX = {0}", IGNORE_URI_REGEX);
    }

    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) res;
            HttpSession session = request.getSession(false);

            String requestURI = request.getRequestURI();

            // Le quita el ContextPath para trabajar más cómodo
            if (this.context.getContextPath().length() > 1) {
                requestURI = requestURI.replace(this.context.getContextPath(), "");
            }

            if (requestURI.matches(IGNORE_URI_REGEX)
                    || (session != null && session.getAttribute(USER_SESSION_ATTRIBUTE_NAME) != null)) {
                chain.doFilter(req, res);
            } else {

                String loginURI = request.getContextPath() + LOGIN_URI;

                /*
                 * Workaround porque el Filter trata de redireccionar al login
                 * el usuario, porque ya no hay SESSION ATTRIBUTE y no le permite
                 * al FullAjaxExceptionHandler de Omnifaces atrapar la excepción
                 * y manejarla como se define en el web.xml
                 */
                if ("partial/ajax".equals(request.getHeader("Faces-Request"))) {
                    response.setContentType("text/xml");
                    response.getWriter()
                            .append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
                            .printf("<partial-response><redirect url=\"%s\"></redirect></partial-response>", loginURI);
                } else {
                    response.sendRedirect(loginURI);
                }
            }
        } catch (IOException | ServletException ex) {
            this.context.log("Sucedió un error con el filtro de autorización", ex);
            LOG.log(Level.SEVERE, "Sucedió un error con el filtro de autorización", ex);
        }
    }

    @Override
    public void destroy() {
        // Do nothing!
    }

}