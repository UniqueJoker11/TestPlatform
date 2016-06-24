package colin.web.monitoring.service;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Administrator on 2016/6/4.
 */
public interface IOperationService {
    /**
     * c采用ICMP进行Ping远程主机
     * @param address
     * @param port
     * @return
     */
    public JSONObject pingRemoteService(String address,int port);
}
