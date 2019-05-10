package cn.doodlister.onlinejudge.vo;

import cn.doodlister.onlinejudge.entity.Notice;
import cn.doodlister.onlinejudge.entity.User;
import cn.doodlister.onlinejudge.utils.HashidUtil;
import cn.doodlister.onlinejudge.utils.Util;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;


@ToString
public class NoticeVo extends BaseVo<Notice>{

    public NoticeVo(Notice clazz) {
        super(clazz,true);
        this.id = HashidUtil.encode(clazz.getId());
        if(this.createByUser!= null){
            this.author = this.createByUser.getUsername();
        }
    }
    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String content;
    @Getter
    @Setter
    private Timestamp createTime;
    @Getter
    @Setter
    private Timestamp lastUpdateTime;
    @Getter
    @Setter
    private Boolean visible;

    private User createByUser;
    @Getter
    @Setter
    private String author;
}
