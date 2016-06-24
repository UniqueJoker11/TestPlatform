package colin.web.monitoring.service.impl;

import colin.web.monitoring.service.IOperationService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2016/6/4.
 */
@Service
public class OperationServiceImpl implements IOperationService{

    private final int PING_TIMEOUT=3000;
    /**
     * c采用ICMP进行Ping远程主机
     *
     * @param address
     * @param port
     * @return
     */
    @Override
    public JSONObject pingRemoteService(String address, int port) {
        JSONObject result=new JSONObject();
        if(StringUtils.isEmpty(address)){
            result.put("success","false");
            result.put("msg","Ip地址不能为空！");
            return result;
        }
        String matchStr="((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";
        if(!address.matches(matchStr)){
            result.put("success","false");
            result.put("msg","Ip地址格式不正确！");
            return result;
        }
        Runtime r = Runtime.getRuntime();  // 将要执行的ping命令,此命令是windows格式的命令
        StringBuilder pingCommand=new StringBuilder("ping "+address);
        if(port!=-1){
            pingCommand.append(":").append(port);
        }
        pingCommand.append(" -n 4").append(" -w ").append(this.PING_TIMEOUT);
        try {   // 执行命令并获取输出
            Process p = r.exec(pingCommand.toString());
            if (p == null) {
                result.put("success","false");
                result.put("msg","Ip地址请求失败！");
                return result;
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));   // 逐行检查输出,计算类似出现=23ms TTL=62字样的次数
            int connectedCount = 0;
            String line = null;
            StringBuilder resultContent=new StringBuilder("");
            while ((line = in.readLine()) != null) {
                resultContent.append(line);
            }
            result.put("success","true");
            result.put("msg",resultContent.toString());
            if(in!=null){
                in.close();
            }
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();   // 出现异常则返回假
            result.put("success","false");
            result.put("msg","Ip地址请求失败！");
            return result;
        }
    }
}
