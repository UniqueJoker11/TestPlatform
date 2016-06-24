package colin.web.monitoring.test.service;

import colin.web.monitoring.service.IOperationService;
import colin.web.monitoring.test.TCommonConfig;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/6/4.
 */
public class TOperationServiceImpl extends TCommonConfig {
    @Autowired
    private IOperationService operationService;

    @Test
    public void testPing(){
        String ipAddress="192.168.1.99";
        int port=-1;
        JSONObject result=operationService.pingRemoteService(ipAddress,port);
        System.out.println("Ping的结果是"+result);
    }
}
