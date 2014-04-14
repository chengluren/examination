package org.dreamer.examination.exception;
/**
 * 考试系统底层基础异常
 * @author xwang
 *
 */
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = -8770418799716389363L;

	public BaseException(String message){
		super(message);
	}
	
	public BaseException(Throwable cause)
	{
		super(cause);
	}
	
	public BaseException(String message,Throwable cause)
	{
		super(message,cause);
	}
}
