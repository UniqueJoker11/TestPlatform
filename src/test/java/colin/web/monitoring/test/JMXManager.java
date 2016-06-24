package colin.web.monitoring.test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

/**
 * 穿件jmx管理器
 * @author Administrator
 *
 */
public class JMXManager {

	public void initJMXServerConnection(String ip,String jmxport,String username,String password){
		String CONNECTION_SERVER="service:jmx:rmi:///jndi/rmi://" + ip + ":"  + jmxport + "/jmxrmi";
		try {
			JMXServiceURL serviceUrl=new JMXServiceURL(CONNECTION_SERVER);
			
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("jmx.remote.credentials", new String[] {username, password});
			JMXConnector connector = JMXConnectorFactory.connect(serviceUrl,  
					params); 
			MBeanServerConnection mbsc = connector.getMBeanServerConnection();
			// 端口最好是动态取得  
			ObjectName threadObjName = new ObjectName("Catalina:type=ThreadPool,name=http-8080");  
			MBeanInfo mbInfo = mbsc.getMBeanInfo(threadObjName);  
			  
			// tomcat的线程数对应的属性值  
			String attrName = "currentThreadCount";  
			MBeanAttributeInfo[] mbAttributes = mbInfo.getAttributes();  
			System.out.println("currentThreadCount:" + mbsc.getAttribute(threadObjName, attrName));  

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedObjectNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AttributeNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ReflectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		JMXManager manager=new JMXManager();
		manager.initJMXServerConnection("192.168.1.83", "8999", "monitorRole", "Colin");
	}
}
