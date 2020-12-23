package result;

import java.util.Map;

public class Result {
	private String from;
	private String to;
	private Map<String ,String> trans_result;
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public Map<String, String> getTrans_result() {
		return trans_result;
	}
	public void setTrans_result(Map<String, String> trans_result) {
		this.trans_result = trans_result;
	}
	public Result(String from, String to, Map<String, String> trans_result) {
		super();
		this.from = from;
		this.to = to;
		this.trans_result = trans_result;
	}
	
}