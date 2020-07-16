package io.spring.trucker.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.DateFormat;
import java.util.Date;

@Entity
public class Alert {
    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String aid;

    @Column(columnDefinition = "VARCHAR(20)")
    private String vin;
    @Column(columnDefinition = "VARCHAR(100)")
    private String description;
    @Column(columnDefinition = "VARCHAR(100)")
    private String priority;
    @Column(columnDefinition = "DATETIME")
    @DateTimeFormat(pattern = "MMM d, yyyy, HH:mm:ss aaa")
    private Date alertGen;

    public String getAid() {
        return aid;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getAlertGen() {

        return DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG).format(alertGen);
    }

    public void setAlertGen(Date alertGen) {
        this.alertGen = alertGen;
    }

    @Override
    public String toString() {
        return "Alert{" +
                "aid='" + aid + '\'' +
                ", vin='" + vin + '\'' +
                ", description='" + description + '\'' +
                ", priority='" + priority + '\'' +
                ", alertGen=" + alertGen +
                '}';
    }
}
