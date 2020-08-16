package com.frontboy.util;
/**
 * 记录异常日志文件的工具类
 * @author zhangjs
 *
 */
public class LogUtil {
	/**
	 * 用于将异常对象e中的错误信息构建为一个String字符串，该字符串
	 * 将用于输出到日志文件当中去
	 * @param e 异常对象
	 * @return 异常对象中的异常信息字符串
	 */
	public static String getMsg(Throwable e){
		//将异常对象中的异常记录转换为记录数组
		StackTraceElement[] s = e.getStackTrace();
		StringBuffer sb = new StringBuffer();
		//从数组中取出异常信息，构建为一个字符串
		for(int i=0;i<s.length;i++){
			sb.append("\r\n");
			sb.append(s[i]);
		}
		return sb.toString();
	}
}
