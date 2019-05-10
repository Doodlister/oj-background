package cn.doodlister.onlinejudge.controller;

import cn.doodlister.onlinejudge.annotation.AccessLimit;
import cn.doodlister.onlinejudge.entity.Notice;
import cn.doodlister.onlinejudge.exception.NotFoundException;
import cn.doodlister.onlinejudge.service.NoticeService;
import cn.doodlister.onlinejudge.vo.NoticeVo;
import cn.doodlister.onlinejudge.vo.PageVo.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;


@RestController
@RequestMapping("/notice")
@Api(tags = "notice")
public class NoticeContoller {

    @Autowired
    NoticeService noticeService;

    /**
     * 创建新的通知
     * @param notice
     */
    @PostMapping
    @ApiOperation(value = "创建通知",notes="创建通知")
    @ResponseStatus(HttpStatus.CREATED)
    public NoticeVo addNotice(@RequestBody Notice notice){
        notice.setCreateTime(new Timestamp(new Date().getTime()));
        notice.setVisible(false);
        Notice saveNotice = noticeService.saveNotice(notice);
        NoticeVo noticeVo = new NoticeVo(saveNotice);
        return noticeVo;
    }

    /**
     * 分页查询通知
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping
    @ApiOperation(value = "分页查询通知",notes="分页查询通知")
    @ResponseStatus(HttpStatus.OK)
    public PageResult<Notice> noticeList(@RequestParam int page,
                                   @RequestParam int pageSize){
        Page<Notice> notice = noticeService.findNoticeByPage(page,pageSize);
        PageResult<Notice> pageResult = new PageResult<>(notice,Notice.class);

        return pageResult;
    }
    /**
     * 根据Id删除通知
     *
     */

    @DeleteMapping("/{id}")
    @ApiOperation(value="根据ID删除通知")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable("id") String id) throws NotFoundException {
        Notice notice = new Notice(id);

        noticeService.delete(notice);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "获取指定IDContent")
    @ResponseStatus(HttpStatus.OK)
    public NoticeVo getNoticeById(@PathVariable("id") String id) throws NotFoundException {
        Notice notice = new Notice(id);
        notice = noticeService.findByID(notice.getId());
        return new NoticeVo(notice);
    }
}
