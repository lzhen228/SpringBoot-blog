package com.wangzaiplus.test.service.impl;

import com.wangzaiplus.test.mapper.BlogHomeMapper;
import com.wangzaiplus.test.pojo.Article;
import com.wangzaiplus.test.pojo.Message;
import com.wangzaiplus.test.pojo.Talk;
import com.wangzaiplus.test.service.BlogHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service("BlogsHomeService")
@Transactional
public class BlogHomeServiceImpl implements BlogHomeService {

    @Resource
    private BlogHomeMapper blogHomeMapper;

    @Override
    public List<Article> findArticleList(Map<String, Object> param) {

        String tag = null;
        switch (Integer.parseInt(param.get("falge").toString())) {
            case 3:
                tag = "前端";
                break;
            case 4:
                tag = "算法";
                break;
            case 5:
                tag = "架构";
                break;
            case 7:
                tag = "生活琐事";
                break;
            case 8:
                tag = "心灵鸡汤";
                break;
            case 9:
                tag = "天马行空";
                break;
            default:
                tag = null;
                break;
        }
        Map<String,Object> map=new HashMap<>();
        map.put("tag",tag);
        map.put("id",param.get("id"));
        map.put("size",param.get("size"));
        return blogHomeMapper.findArticleList(map);
    }

    @Override
    public List<Article> findQueryArticle(Map<String, Object> param) {
        return blogHomeMapper.findQueryArticle(param);
    }

    @Override
    public Boolean updateReadNum(Integer id) {
        int count = blogHomeMapper.updateReadNum(id);
        if (count > 0){
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateLoveNum(Integer id) {
        int count = blogHomeMapper.updateLoveNum(id);
        if (count > 0){
            return true;
        }
        return false;
    }

    @Override
    public List<Talk> findTalkList(Integer id) {
        List<Talk> talkList = blogHomeMapper.findTalkList(id);
        Iterator<Talk> it = talkList.iterator();
        while (it.hasNext()) {
            Talk item = it.next();
            // 是回复其他评论的
            if (item.getTid() != 0) {
                for (Talk ele : talkList) {
                    if (ele.getTid() == 0 && ele.getId() == item.getTid()) {
                        // 添加到该评论的回复中
                        ele.addReplyTalk(item);
                    }
                    it.remove();
                    break;
                }
            }
        }
        return talkList;
    }


    @Override
    public Boolean insertTalk(Talk talk) {
        Integer count = blogHomeMapper.insertTalk(talk);
        if (count > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Article> findQueryString(String title) {
        return blogHomeMapper.findQueryString(title);
    }

    @Override
    public List<Message> findMessageList() {
        return blogHomeMapper.findMessageList();
    }

    @Override
    public Boolean insertMessage(String content) {
        Integer count = blogHomeMapper.insertMessage(content);
        if (count > 0){
            return true;
        }
        return false;
    }


}
