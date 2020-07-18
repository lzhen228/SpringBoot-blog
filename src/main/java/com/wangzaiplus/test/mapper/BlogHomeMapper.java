package com.wangzaiplus.test.mapper;

import com.wangzaiplus.test.pojo.Article;
import com.wangzaiplus.test.pojo.Message;
import com.wangzaiplus.test.pojo.Talk;
import java.util.List;
import java.util.Map;

public interface BlogHomeMapper {
    List<Article> findArticleList(Map<String, Object> param);

    List<Article> findQueryArticle(Map<String, Object> param);

    Integer updateReadNum(Integer id);

    Integer updateLoveNum(Integer id);

    List<Talk> findTalkList(Integer id);

    Integer insertTalk(Talk talk);

    List<Article> findQueryString(String title);

    List<Message> findMessageList();

    Integer insertMessage(String content);
}
