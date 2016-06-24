package colin.web.monitoring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2016/5/5.
 */
@Controller
@RequestMapping("dashboard")
public class DashboardController extends CommonController{
    private Logger logger= LoggerFactory.getLogger(DashboardController.class);
    /**
     * 显示控制面板
     * @return
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView showDashBoardHtml(){
        return new ModelAndView("dashboard");
    }

}
