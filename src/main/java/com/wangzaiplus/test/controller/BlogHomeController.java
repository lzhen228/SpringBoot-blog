package com.wangzaiplus.test.controller;

import com.sun.org.apache.xpath.internal.objects.XString;
import com.wangzaiplus.test.pojo.ApiRespones;
import com.wangzaiplus.test.pojo.Article;
import com.wangzaiplus.test.pojo.Message;
import com.wangzaiplus.test.pojo.Talk;
import com.wangzaiplus.test.service.BlogHomeService;
import com.wangzaiplus.test.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/index/")
public class BlogHomeController {
	@Autowired
	private BlogHomeService blogHomeService;

	@RequestMapping(value = "findArticleList",method = RequestMethod.GET)
	@ResponseBody
	public ApiRespones findArticleList(@RequestParam("id") Integer id,
									   @RequestParam("falge") Integer falge,
									   @RequestParam("size") Integer size){

		Map<String, Object> param= new HashMap<>();

		if(id!=null&&id!=0) param.put("id",id);
		if(falge!=null) param.put("falge",falge);
		if(size!=null) param.put("size",size);

		ApiRespones<Map<String, Object>> respones = ApiRespones.OK();
		Map<String, Object> result= new HashMap<>();
		try{
			List<Article> articleList = blogHomeService.findArticleList(param);
			param.put("size",null);
			int count = blogHomeService.findArticleList(param).size();
			result.put("data", articleList);
			result.put("count", count);
			respones.setResult(result);
			respones.setCode(0);
			respones.setMessage("请求成功！");
		}catch (Exception e){
			respones.setCode(0);
			respones.setMessage("请求失败！");
			log.error("{}",e);
		}
		return respones;
	}
	
	@RequestMapping(value = "findQueryArticle",method = RequestMethod.GET)
	@ResponseBody
	public ApiRespones findArticleList(@RequestParam("title") String title){
		Map<String, Object> param= new HashMap<>();
		if(title !=null){
			param.put("title",title);
		}

		ApiRespones<Map<String, Object>> respones = ApiRespones.OK();
		Map<String, Object> result= new HashMap<>();
		try{
			List<Article> articleList  = blogHomeService.findQueryArticle(param);
			result.put("data", articleList);
			respones.setResult(result);
			respones.setCode(0);
			respones.setMessage("请求成功！");
		}catch (Exception e){
			respones.setCode(0);
			respones.setMessage("请求失败！");
			log.error("{}",e);
		}
		return respones;
	}

	@RequestMapping(value = "findthisArticle",method = RequestMethod.GET)
	@ResponseBody
	public ApiRespones findthisArticle(@RequestParam("id") Integer id){
		Map<String, Object> param= new HashMap<>();
		if(id !=null  ){
			param.put("id",id);

		}


		ApiRespones <Map<String, Object>> respones = ApiRespones.OK();
		Map<String, Object> result= new HashMap<>();
		//只有id是有用的
		param.put("falge",1);
		param.put("size",1);
		List<Article> nextArticle = new ArrayList<Article>();
		List<Article> lastArticle = new ArrayList<Article>();
		try{
			List<Article> thisArticle = blogHomeService.findArticleList(param);
			if (thisArticle.get(0).getMinid() < id) {
				param.put("id",id - 1);
				lastArticle = blogHomeService.findArticleList(param);
			} else {
				lastArticle.add(new Article()); // 防止报错
			}
			if (thisArticle.get(0).getMaxid() > id) {
				param.put("id",id + 1);
				nextArticle = blogHomeService.findArticleList(param);
			} else {
				nextArticle.add(new Article()); //
			}
			result.put("last", lastArticle.get(0));
			result.put("next", nextArticle.get(0));
			result.put("this", thisArticle.get(0));
			respones.setResult(result);
			respones.setCode(0);
			respones.setMessage("请求成功！");
		}catch (Exception e) {
			respones.setCode(0);
			respones.setMessage("请求失败！");
			log.error("{}", e);
		}
		return respones;
	}

	@RequestMapping(value = "updateReadNum",method = RequestMethod.GET)
	@ResponseBody
	public ApiRespones updateReadNum(@RequestParam("id") Integer id) throws IOException {
		ApiRespones <Map<String, Object>> respones = ApiRespones.OK();
		Map<String, Object> param= new HashMap<>();
		Boolean msg = false;
		try {
			msg = blogHomeService.updateReadNum(id);
			param.put("msg", msg);
			respones.setResult(param);
			respones.setCode(0);
			respones.setMessage("请求成功！");
		}catch (Exception e) {
			respones.setCode(0);
			respones.setMessage("请求失败！");
			log.error("{}", e);
		}
			return respones;
	}

	@RequestMapping(value = "updateLoveNum",method = RequestMethod.GET)
	@ResponseBody
	public ApiRespones updateLoveNum(@RequestParam("id") Integer id){
		ApiRespones <Map<String, Object>> respones = ApiRespones.OK();
		Map<String, Object> param= new HashMap<>();
		Boolean msg = false;
		try {
			msg = blogHomeService.updateLoveNum(id);
			param.put("msg", msg);
			respones.setResult(param);
			respones.setCode(0);
			respones.setMessage("请求成功！");
		}catch (Exception e) {
			respones.setCode(0);
			respones.setMessage("请求失败！");
			log.error("{}", e);
		}
			return respones;
	}
	
	@RequestMapping(value = "findTalkList",method = RequestMethod.GET)
	@ResponseBody
	public ApiRespones findTalkList(@RequestParam("id") Integer id){
		ApiRespones <Map<String, Object>> respones = ApiRespones.OK();
		Map<String, Object> param= new HashMap<>();
		try {
			List<Talk> talkList = blogHomeService.findTalkList(id);
			param.put("data", talkList);
			respones.setResult(param);
			respones.setCode(0);
			respones.setMessage("请求成功！");
		}catch (Exception e) {
			respones.setCode(0);
			respones.setMessage("请求失败！");
			log.error("{}", e);
		}
			return respones;
	}
	
	@RequestMapping(value = "insertTalk",method = RequestMethod.GET)
	@ResponseBody
	public ApiRespones insertTalk(@RequestParam("talkJson") String talkJson){
		Talk talk = CommonUtil.formatTotTalk(talkJson);
		ApiRespones <Map<String, Object>> respones = ApiRespones.OK();
		Map<String, Object> param= new HashMap<>();
		Boolean msg = false;
		try{
			msg = blogHomeService.insertTalk(talk);
			param.put("msg", msg);
			respones.setResult(param);
			respones.setCode(0);
			respones.setMessage("请求成功！");
		}catch (Exception e) {
			respones.setCode(0);
			respones.setMessage("请求失败！");
			log.error("{}", e);
		}
			return respones;
	}
	
	@RequestMapping(value = "/findQueryString",method = RequestMethod.GET)
	@ResponseBody
	public ApiRespones findQueryString(@RequestParam("title") String title){
		ApiRespones <Map<String, Object>> respones = ApiRespones.OK();
		Map<String, Object> param= new HashMap<>();
		try {
			List<Article> results = blogHomeService.findQueryString(title);
			param.put("data", results);
			respones.setResult(param);
			respones.setCode(0);
			respones.setMessage("请求成功！");
		}catch (Exception e) {
			respones.setCode(0);
			respones.setMessage("请求失败！");
			log.error("{}", e);
		}
		return respones;
	}
	
	@RequestMapping(value = "/findMessageList",method = RequestMethod.GET)
	@ResponseBody
	public ApiRespones findMessageList(){
		ApiRespones <Map<String, Object>> respones = ApiRespones.OK();
		Map<String, Object> param= new HashMap<>();
		try {
			List<Message> results = blogHomeService.findMessageList();
			param.put("data", results);
			respones.setResult(param);
			respones.setCode(0);
			respones.setMessage("请求成功！");
		}catch (Exception e) {
			respones.setCode(0);
			respones.setMessage("请求失败！");
			log.error("{}", e);
		}
		return respones;
	}
	
	@RequestMapping(value = "/insertMessage",method = RequestMethod.GET)
	@ResponseBody
	public ApiRespones insertMessage(@RequestParam("content") String content){
		ApiRespones <Map<String, Object>> respones = ApiRespones.OK();
		Map<String, Object> param= new HashMap<>();
		Boolean msg = false;
		try {
			msg = blogHomeService.insertMessage(content);
			param.put("msg", msg);
			respones.setResult(param);
			respones.setCode(0);
			respones.setMessage("请求成功！");
		}catch (Exception e) {
			respones.setCode(0);
			respones.setMessage("请求失败！");
			log.error("{}", e);
		}
		return respones;
	}

}
