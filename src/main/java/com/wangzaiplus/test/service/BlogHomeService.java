package com.wangzaiplus.test.service;

import com.wangzaiplus.test.pojo.Article;
import com.wangzaiplus.test.pojo.Message;
import com.wangzaiplus.test.pojo.Talk;

import java.util.List;
import java.util.Map;

public interface BlogHomeService {
    List<Article> findArticleList(Map<String, Object> param);

    List<Article> findQueryArticle(Map<String, Object> param);

    Boolean updateReadNum(Integer id);

    Boolean updateLoveNum(Integer id);

    List<Talk> findTalkList(Integer id);

    Boolean insertTalk(Talk talk);

    List<Article> findQueryString(String title);

    List<Message> findMessageList();

    Boolean insertMessage(String content);
}
