package com.wangzaiplus.test.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

public class Article implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Integer id;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String title;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String content;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private  Integer isTop;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String point;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String tag;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String time;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Integer leaveNum;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Integer loveNum;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Integer readNum;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Integer minid;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Integer maxid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Integer getIsTop() {
		return isTop;
	}
	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getLeaveNum() {
		return leaveNum;
	}
	public void setLeaveNum(Integer leaveNum) {
		this.leaveNum = leaveNum;
	}
	public Integer getLoveNum() {
		return loveNum;
	}
	public void setLoveNum(Integer loveNum) {
		this.loveNum = loveNum;
	}
	public Integer getReadNum() {
		return readNum;
	}
	public void setReadNum(Integer readNum) {
		this.readNum = readNum;
	}
	public Integer getMinid() {
		return minid;
	}
	public void setMinid(Integer minid) {
		this.minid = minid;
	}
	public Integer getMaxid() {
		return maxid;
	}
	public void setMaxid(Integer maxid) {
		this.maxid = maxid;
	}
	
	
	
}
