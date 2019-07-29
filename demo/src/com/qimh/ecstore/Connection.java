package com.qimh.ecstore;




import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

/**
 * author qimh
 * remote-branch
 */
public class Connection {

	public static final String DEFAULT_CHARSET = "UTF-8";
	private static final String METHOD_POST = "POST";
	private static final String METHOD_GET = "GET";
	private static DefaultHttpClient httpClient = null;
	private static HttpResponse response = null;
	
	
	
	//阿里云ecstore
	private static final String	ECSheng = "http://m.mewxh.com/index.php/openapi/cellphone/api";
//	private static final String	ECSheng = "http://m.mewxh.com/index.php/openapi/syncnovo/get_pressed_coupons";
//	private static final String	ECSheng = "http://m.mewxh.com/index.php/openapi/appapisasa/getPamMemInfo";
	
	
	
	public static void main(String[] args) {
		Connection.checkOut("882c8567edc3e08df9830e25d70df54b", "0");
	}
	
	
	/**
	 * @author qimh
	 * @access public 
	 * @description 购物车结算
	 * @param String session
	 * @return void 
	 */
	public static void checkOut(String session,String point){
		
		Map mapd = new HashMap();
		mapd.put("key","session" );
		mapd.put("value",session);
		
		Map mapd2 = new HashMap();
		mapd2.put("key","supplierId" );
		mapd2.put("value","8");
		
		Map mapd3 = new HashMap();
		mapd3.put("key","point" );
		mapd3.put("value",point);
		
		List listattr = new ArrayList();
		listattr.add(mapd);
		listattr.add(mapd2);
		listattr.add(mapd3);
		JSONObject json = Connection.shPostMethond("cart.checkout",listattr);
		
	}
	

public static JSONObject shPostMethond(String apimenthod, List<Map<String,Object>> listparam){
		
		JSONObject jsonObject = new JSONObject();
		try {
			System.out.println("start ecstore url-------------------->"+ECSheng );
//			System.out.println("start eclogin-------------------->"+apimenthod );
			
			httpClient = new DefaultHttpClient();
			
			HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), 90000);//设置连接超时
			HttpConnectionParams.setSoTimeout(httpClient.getParams(), 90000);//设置请求超时
			String secret = "";//
			
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("method", apimenthod));
			nvps.add(new BasicNameValuePair("api_version", "1.0"));
			
			Hashtable<String,Object> params = new Hashtable<String,Object>();
			params.put("method", apimenthod);// 请求的接口名称
			params.put("api_version", "1.0");
			
			if(listparam != null){
				for(int i = 0 ; i < listparam.size() ; i++){
					Map<String,Object> map = (Map<String,Object>)listparam.get(i);
					String key = (String)map.get("key");
					String value = (String)map.get("value");
					nvps.add(new BasicNameValuePair(key,value));
					params.put(key,value );
				}
			}
			
			String s = gen_sign(params,secret);
			nvps.add(new BasicNameValuePair("sign", s));
			
			String ctype = "application/x-www-form-urlencoded;charset=" + DEFAULT_CHARSET;
			HttpPost httpPost= setPostHead(ECSheng,ctype);

			
			Date date = new Date();
			long start = date.getTime();
			
			httpPost.setEntity(new UrlEncodedFormEntity(nvps,DEFAULT_CHARSET));
			response = httpClient.execute(httpPost);
			
		    Date date2 = new Date();
			long end = date2.getTime();
			
			System.out.println("ECapimenthod---------------------->"+apimenthod);
			
			System.out.println("时差："+(end-start)+"毫秒");
			
			int statuscode = response.getStatusLine().getStatusCode();
			System.out.println("statuscode eclogin-------------------->"+statuscode);
			//token参数截取
			String pageCode = EntityUtils.toString(response.getEntity());
//			System.out.println("pageCode="+pageCode);
			if(pageCode.indexOf("{") > 0)
			{
				pageCode = pageCode.substring(pageCode.indexOf("{"),pageCode.length());
			}
			jsonObject = new JSONObject(pageCode.replace("??????", ""));
			System.out.println("返回结果："+jsonObject.toString());
////			
//			JSONArray jsa = (JSONArray) JSONSerializer.toJSON(pageCode);
//			
//			System.out.println(jsa.toString());
			
//			JSONArray array = job.getJSONArray("data");
//			jsonObject j'son  = job.getJSONObject("data");
//			System.out.println(java.net.URLDecoder.decode(job.getString("res"),"gbk"));
//			System.out.println(array.toString());
			
		
		} catch (Exception e) {
			// TODO: handle exception
			jsonObject.put("error", "notconn");
			e.printStackTrace();
		}
		return jsonObject;
	}

	public static  HttpPost setPostHead(String Url,String ctype){
		HttpPost httpPost=new HttpPost(Url);
		httpPost.setHeader("Accept", "text/xml,text/javascript,textml");
		httpPost.setHeader("User-Agent", "shopEx");
		httpPost.setHeader("Content-Type", ctype);
		return httpPost;
	
	}
	
	
	public static String gen_sign(Hashtable<String,Object> th,String token){
		//php写法 strtoupper(md5(strtoupper(md5(assemble($params))).$token));
		String sign=DigestUtils.md5Hex(DigestUtils.md5Hex(assemble(th)).toUpperCase()+token).toUpperCase();
		return sign;
	}

	/**
	 * assmble在php版本中有递归功能,java版块考虑具体传参数值没有Hashtable类型，简单化处理。
	 * @param th
	 * @return
	 */
	private static String assemble(Hashtable<String,Object> th){
		if(th.isEmpty()){
			return null;
		}
		Enumeration<String> e=th.keys();
		ArrayList<String> al=new ArrayList<String>();
		
		while(e.hasMoreElements()){
			al.add(e.nextElement());
		}
	
		java.util.Collections.sort(al);
		
		String sign="";
		
		for(Iterator<String> it=al.iterator();it.hasNext();){
			String next=it.next();
			Object nvalue=th.get(next);
			if(nvalue instanceof Hashtable){
				sign+=next+assemble((Hashtable<String, Object>)nvalue);
			}else{
				sign+=next+th.get(next);
			}
			
		}
		
		return sign;
		
	}
	
}
