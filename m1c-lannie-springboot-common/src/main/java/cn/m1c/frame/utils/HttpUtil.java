package cn.m1c.frame.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * 2016年8月5日 封装http请求 
 * @author  phil(s@m1c.cn,m1c softCo.,ltd)
 * @version lannie
 */
public class HttpUtil {
	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	//-----------------------定义json格式请求-------------------------------------
	private static final String APPLICATION_JSON = "application/json";
    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";
	    
	/**
	 * post提交(Content-Type=application/json)
	 * 
	 * @param url  请求url
	 * @param parameters json格式请求参数
	 * @return
	 */
	public static String postJson(String url , String parameters){
		String body = null;
		 // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost method = new HttpPost(url);
		method.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
		try {
			//设置请求实体
			StringEntity se = new StringEntity(parameters,"utf-8");
			se.setContentType(CONTENT_TYPE_TEXT_JSON);
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
			method.setEntity(se);
			//执行请求
			HttpResponse response = httpclient.execute(method);
			//查询返回结果
			int statusCode = response.getStatusLine().getStatusCode();
			logger.info("response code : {}",statusCode);
			body = EntityUtils.toString(response.getEntity());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			//释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return body;
	}

	public static String sendPostRequestHttpClient(String path, Map<String, String> params, String encode)
			throws Throwable {
		List<NameValuePair> paramspairs = new ArrayList<NameValuePair>();
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				paramspairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramspairs, encode);// 对请求参数进行编码,得到实体数据
		HttpPost post = new HttpPost(path);
		post.setEntity(entity);
		CloseableHttpClient httpclient = HttpClients.createDefault();// 建立浏览器对象
		HttpResponse response = httpclient.execute(post);
		String body = EntityUtils.toString(response.getEntity());
		return body;
	}
	
	/**
	 * 从http请求中获取json
	 * @param request
	 */
	public static String paramToJson(HttpServletRequest request){
		try {
			StringBuffer sb = new StringBuffer();
			BufferedInputStream is = new BufferedInputStream(request.getInputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 把对象转成json格式并写入response
	 *
	 * @param response
	 * @param value
	 */
	public static void write(HttpServletResponse response,Object value){
		PrintWriter out  = null;
		try {
			response.setContentType("application/json; charset=utf-8");
			out = response.getWriter();
			Object json = JSONObject.toJSON(value); 
			out.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(out!=null){
				out.flush();
				out.close();
			}
		}
	}
}
