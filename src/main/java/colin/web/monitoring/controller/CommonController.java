package colin.web.monitoring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2016/5/6.
 */
public class CommonController {
    @Autowired
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession httpSession;

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }
    @ModelAttribute
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public HttpSession getHttpSession() {
        if(httpSession==null){
            httpSession=request.getSession(true);
        }
        return httpSession;
    }

    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }
}
