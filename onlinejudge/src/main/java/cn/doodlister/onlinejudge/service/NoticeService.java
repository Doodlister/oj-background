package cn.doodlister.onlinejudge.service;

import cn.doodlister.onlinejudge.dao.NoticeDao;
import cn.doodlister.onlinejudge.entity.Notice;
import cn.doodlister.onlinejudge.entity.User;
import cn.doodlister.onlinejudge.exception.AuthenticationException;
import cn.doodlister.onlinejudge.vo.Result;
import cn.doodlister.onlinejudge.vo.UserVo;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoticeService {
   @Autowired
   NoticeDao noticeDao;

    public Notice saveNotice(Notice notice){

        Notice saveNotice = noticeDao.save(notice);
        return saveNotice;
    }

    public Page<Notice> findNoticeByPage(int page, int pageSize) {
        Page<Notice> pageList = noticeDao.findAll(PageRequest.of(page, pageSize));

        return pageList;
    }
    public void delete(Notice notice) {

        noticeDao.delete(notice);
    }

    public Notice findByID(Integer id) {

        Optional<Notice> notice = noticeDao.findById(id);
        if(notice != null) {
            return notice.get();
        }else{
            return null;
        }

    }
}
