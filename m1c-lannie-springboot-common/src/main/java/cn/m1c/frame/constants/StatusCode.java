package cn.m1c.frame.constants;

/**
 * 2016.7.27
 * 说明:全站状态代码<br>
 * 约定：<br>
 * 1.name使用小写，便于直观<br>
 * 2.所属模块消息，请使用模块名称开始，如car_xx、order_xxx
 * 3.所有错误代码均为负数<br>
 * 4.所有正确代码均为正数<br>
 * 5.状态代码*_*_*_*_* 第一个*指代模块名,第二个*指代模块中具体的操作，最后一个*指代错误或者是成功，中间的*指代的是操作错误的原因
 * @author  phil(s@m1c.cn,m1c softCo.,ltd)
 * @version lannie
 */
public class StatusCode {
	
	public static StatusCode success = new StatusCode(Integer.MAX_VALUE, 				"成功");
	public static StatusCode failed = new StatusCode(Integer.MIN_VALUE, 				"失败");
	//http 相关状态码
	public static StatusCode forbidden = new StatusCode(-403, 				"非法");
	public static StatusCode timeout = new StatusCode(-408, 				"超时");
	
/**
 * person 模块  code 范围1100 - 1200
 */
	public static StatusCode person_login_pwd_error = new StatusCode(-1101, 				"密码错误");
	public static StatusCode person_login_oldpwd_error = new StatusCode(-1102, 				"原密码错误");
	public static StatusCode person_login_loginname_empty_error = new StatusCode(-1103, 	"无此用户,请联系管理员");

	private Integer code;
	private String message;
	
	protected StatusCode(Integer code, String message){
		this.code = code;
		this.message = message;
	}
	
	public Integer getCode(){return this.code;}
	public String getMessage(){return this.message;}
	public void setMessage(String message){this.message = message;}
	
	@Override
	public String toString(){
		return "{\"code\":" + getCode() + ",\"message\":\"" + getMessage() + "\"}";
	}
	
}