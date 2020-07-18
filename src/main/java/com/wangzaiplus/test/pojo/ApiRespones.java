package com.wangzaiplus.test.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 前端返回值基础类
 */
@Data
public class ApiRespones<T> {
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private int code;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String message ;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private T result;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Long startDate;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Long endDate;

	public static <T> ApiRespones<T> OK(T result){
		ApiRespones<T> apiRespones = new ApiRespones();
		apiRespones.setCode(0);
		if(result != null){
			apiRespones.setResult(result);
		}
		return apiRespones;
	}

	public static  ApiRespones OK(){
		return OK(null);
	}


	public static <T> ApiRespones FAIL(){
		return OK(null);
	}

	public static <T> ApiRespones FAIL(String message){
		ApiRespones apiRespones = new ApiRespones();
		apiRespones.setCode(1);
		if(message != null){
			apiRespones.setMessage(message);
		}
		return apiRespones;
	}



}
