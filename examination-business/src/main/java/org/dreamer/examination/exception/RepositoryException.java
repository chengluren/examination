package org.dreamer.examination.exception;

/**
 * 考试系统业务层异常 
 * @author xwang
 *
 */
public class RepositoryException extends BaseException{

	private static final long serialVersionUID = -540964131892967631L;

	public RepositoryException(String message){
		super(message);
	}
	
	public RepositoryException(Throwable cause)
	{
		super(cause);
	}
	
	public RepositoryException(String message,Throwable cause)
	{
		super(message,cause);
	}
}
