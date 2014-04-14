package org.dreamer.examination.exception;

/**
 * 考试系统数据层异常 
 * @author xwang
 *
 */
public class BusinessException extends BaseException{

	private static final long serialVersionUID = 3555395075124808509L;

	public BusinessException(String message){
		super(message);
	}
	
	public BusinessException(Throwable cause)
	{
		super(cause);
	}
	
	public BusinessException(String message,Throwable cause)
	{
		super(message,cause);
	}
}
