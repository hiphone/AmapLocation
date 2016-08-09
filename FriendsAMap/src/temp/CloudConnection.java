package temp;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.amap.protocol.HTTPUtils;
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : @AMapCloud
//  @ File Name : CloudHelper.java
//  @ Date : 2016/8/6
//  @ Author : @haifeng.gong hongguang.jin
//
//

public abstract class CloudConnection {
	public HttpService connection;
	public String tableId = null;

	public CloudConnection() {
		connection = new HttpService();
	}

	public String insert(Object data) {
		return connection.HttpPost(HTTPUtils.INSERT,
				toNameValuePairData(parseData(data)));
	}

	public String delete(String id) {
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("key", HTTPUtils.WEBAPIKEY));
		formparams.add(new BasicNameValuePair("tableid", tableId));
		formparams.add(new BasicNameValuePair("ids", id));
		return connection.HttpPost(HTTPUtils.DELETE, formparams);
	}

	public String update(Object data) {
		return connection.HttpPost(HTTPUtils.UPDATE,
				toNameValuePairData(parseData(data)));
	}

	public String query(String filter) {
		return connection.HttpGet(HTTPUtils.LOCAL_QUERY + "tableid=" + tableId
				+ "&city=全国&keywords=&filter=" + filter + "&key="
				+ HTTPUtils.WEBAPIKEY);
	}

	public void setTableId(String tableID) {
		this.tableId = tableID;
	}

	public List<NameValuePair> toNameValuePairData(JSONObject data) {
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("key", HTTPUtils.WEBAPIKEY));
		formparams.add(new BasicNameValuePair("tableid", tableId));
		formparams.add(new BasicNameValuePair("loctype", "2"));
		formparams.add(new BasicNameValuePair("data", data.toString()));
		return formparams;
	}

	public abstract JSONObject parseData(Object data);
}
