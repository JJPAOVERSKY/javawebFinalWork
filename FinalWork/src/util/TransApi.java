package util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class TransApi {
	 private static final String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";
	    private String appid;
	    private String securityKey;
	    public TransApi(String appid, String securityKey) {
	        this.appid = appid;
	        this.securityKey = securityKey;
	    }

	    public String getTransResult(String query, String from, String to) {
	        Map<String, String> params = buildParams(query, from, to);
	        return HttpGet.get(TRANS_API_HOST, params);
	    }

	    private Map<String, String> buildParams(String query, String from, String to) {
	        Map<String, String> params = new HashMap<String, String>();
	        params.put("q", query);
	        params.put("from", from);
	        params.put("to", to);
	        params.put("appid", appid);
	        // 随机数
	        String salt = String.valueOf(System.currentTimeMillis());
	        params.put("salt", salt);
	        // 签名
	        String src = appid + query + salt + securityKey; // 加密前的原文
	        try {
				URLEncoder.encode(src, "utf-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        try {
				params.put("sign", MD5.md5(src));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        return params;
	    }

}
