package pojo;

import java.util.Date;

public class Music {
    private int musicId;
    private String name;
    private String author;
    private String address;
    private String img;
    private Date createTime;
    private Date changeTime;
    private String remark;

    public Music(){
    }

    @Override
    public String toString() {
        return "music{" +
                "musicId=" + musicId +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", address='" + address + '\'' +
                ", img='" + img + '\'' +
                ", createTime=" + createTime +
                ", changeTime=" + changeTime +
                ", remark='" + remark + '\'' +
                '}';
    }

    public int getMusicId() {
        return musicId;
    }

    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
