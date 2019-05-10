package cn.doodlister.onlinejudge.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "judge_server", schema = "onlinejudge", catalog = "")
public class JudgeServer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.TABLE)
    private int id;
    @Basic
    @Column(name = "hostname")
    private String hostname;
    @Basic
    @Column(name = "ip")
    private String ip;
    @Basic
    @Column(name = "judger_version")
    private String judgerVersion;
    @Basic
    @Column(name = "cpu_core")
    private int cpuCore;
    @Basic
    @Column(name = "memory_usage")
    private double memoryUsage;
    @Basic
    @Column(name = "cpu_usage")
    private double cpuUsage;
    @Basic
    @Column(name = "last_heartbeat")
    private Timestamp lastHeartbeat;
    @Basic
    @Column(name = "create_time")
    private Timestamp createTime;
    @Basic
    @Column(name = "task_number")
    private int taskNumber;
    @Basic
    @Column(name = "service_url")
    private String serviceUrl;
    @Basic
    @Column(name = "is_disabled")
    private Boolean isDisabled;




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JudgeServer that = (JudgeServer) o;
        return id == that.id &&
                cpuCore == that.cpuCore &&
                Double.compare(that.memoryUsage, memoryUsage) == 0 &&
                Double.compare(that.cpuUsage, cpuUsage) == 0 &&
                taskNumber == that.taskNumber &&
                isDisabled == that.isDisabled &&
                Objects.equals(hostname, that.hostname) &&
                Objects.equals(ip, that.ip) &&
                Objects.equals(judgerVersion, that.judgerVersion) &&
                Objects.equals(lastHeartbeat, that.lastHeartbeat) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(serviceUrl, that.serviceUrl);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, hostname, ip, judgerVersion, cpuCore, memoryUsage, cpuUsage, lastHeartbeat, createTime, taskNumber, serviceUrl, isDisabled);
    }
}
