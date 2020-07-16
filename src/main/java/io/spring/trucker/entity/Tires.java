package io.spring.trucker.entity;

import javax.persistence.*;


/*
* "tires": {
      "frontLeft": 34,
      "frontRight": 36,
      "rearLeft": 29,
      "rearRight": 34
   }*/
@Entity
public class Tires {

    @Id
    @Column(columnDefinition = "VARCHAR(10)")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer tid;

    @Column(columnDefinition = "INTEGER(10)")
    private Integer frontLeft;
    @Column(columnDefinition = "INTEGER(10)")
    private Integer frontRight;
    @Column(columnDefinition = "INTEGER(10)")
    private Integer rearLeft;
    @Column(columnDefinition = "INTEGER(10)")
    private Integer rearRight;

    public Tires() {

    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getFrontLeft() {
        return frontLeft;
    }

    public void setFrontLeft(int frontLeft) {
        this.frontLeft = frontLeft;
    }

    public int getFrontRight() {
        return frontRight;
    }

    public void setFrontRight(int frontRight) {
        this.frontRight = frontRight;
    }

    public int getRearLeft() {
        return rearLeft;
    }

    public void setRearLeft(int rearLeft) {
        this.rearLeft = rearLeft;
    }

    public int getRearRight() {
        return rearRight;
    }

    public void setRearRight(int rearRight) {
        this.rearRight = rearRight;
    }

    @Override
    public String toString() {
        return "Tires{" +
                "tid=" + tid +
                ", frontLeft=" + frontLeft +
                ", frontRight=" + frontRight +
                ", rearLeft=" + rearLeft +
                ", rearRight=" + rearRight +
                '}';
    }
}
