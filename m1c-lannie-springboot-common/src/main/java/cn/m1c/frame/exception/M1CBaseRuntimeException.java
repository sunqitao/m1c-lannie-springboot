package cn.m1c.frame.exception;

/**
 * 2016年7月27日 异常基类，所有都为非强制检查异常
 * @author  phil(s@m1c.cn,m1c softCo.,ltd)
 * @version lannie
 */
public abstract class M1CBaseRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -7307961667791435686L;
	
	/**
	 * 避免OSGi环境中调用getMessage()方法出现死锁
	 
	static{
		ExceptionUtils.class.getName();
	}*/

	public M1CBaseRuntimeException(String message) {
		super(message);
	}
	
	public M1CBaseRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public String getMessage() {
		return buildMessage(super.getMessage(), getCause());
	}
	/**
	 * 为基本信息和根异常绑定消息
	 */
	public static String buildMessage(String message, Throwable cause) {
		if (cause != null) {
			StringBuilder sb = new StringBuilder();
			if (message != null) {
				sb.append(message).append("; ");
			}
			sb.append("base exception is ").append(cause);
			return sb.toString();
		}
		else {
			return message;
		}
	}
	/**
	 * 获取异常根原因  如果没有向上追溯到原因，则返回自己。
	 */
	public Throwable getRootCause(){
		Throwable rootCause = null;
		Throwable cause = getCause();
		while (cause != null && cause != rootCause) {
			rootCause = cause;
			cause = cause.getCause();
		}
		return rootCause == null ? this : rootCause;
	}
	
	/**
	 * 判断是否异常包含一个给定类型的异常
	 */
	public boolean contains(Class<?> exceptionClass) {
		if (exceptionClass == null) {
			return false;
		}
		if (exceptionClass.isInstance(this)) {
			return true;
		}
		Throwable cause = getCause();
		if (cause == this) {
			return false;
		}
		if (cause instanceof M1CBaseRuntimeException) {
			return ((M1CBaseRuntimeException) cause).contains(exceptionClass);
		}
		else {
			while (cause != null) {
				if (exceptionClass.isInstance(cause)) {
					return true;
				}
				if (cause.getCause() == cause) {
					break;
				}
				cause = cause.getCause();
			}
			return false;
		}
	}
}
