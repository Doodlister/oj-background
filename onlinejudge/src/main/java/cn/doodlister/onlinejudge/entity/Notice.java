package cn.doodlister.onlinejudge.entity;


import cn.doodlister.onlinejudge.exception.NotFoundException;
import cn.doodlister.onlinejudge.utils.HashidUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
public class Notice {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.TABLE)
    private Integer id;

    @Basic
    @Column(name = "title")
    private String title;

    @Basic
    @Column(name = "content")
    private String content;

    @Basic
    @Column(name = "create_time")
    private Timestamp createTime;

    @Basic
    @Column(name = "last_update_time")
    private Timestamp lastUpdateTime;

    @Basic
    @Column(name = "visible")
    private Boolean visible;

    @ManyToOne
    @JoinColumn
    private User createByUser;

    public Notice(){}
    public Notice(String id) throws NotFoundException {
        this.id = HashidUtil.decode(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notice notice = (Notice) o;
        return id == notice.id &&
                visible == notice.visible &&
                Objects.equals(title, notice.title) &&
                Objects.equals(content, notice.content) &&
                Objects.equals(createTime, notice.createTime) &&
                Objects.equals(lastUpdateTime, notice.lastUpdateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, content, createTime, lastUpdateTime, visible);
    }
}
