package com.andy.jaa.ec.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by quanxi on 2018/3/21.
 */
@Entity(nameInDb = "UserProfile")
public class UserProfile {
    @Id(autoincrement = false)
    @Property(nameInDb = "userId")
    private String userId;
    @Property(nameInDb = "name")
    private String name;
    @Property(nameInDb = "avatar")
    private String avatar;
    @Property(nameInDb = "gender")
    private String gender;
    @Property(nameInDb = "address")
    private String address;
    @Property(nameInDb = "password")
    private String passWord;
    @Generated(hash = 2130364397)
    public UserProfile(String userId, String name, String avatar, String gender,
            String address, String passWord) {
        this.userId = userId;
        this.name = name;
        this.avatar = avatar;
        this.gender = gender;
        this.address = address;
        this.passWord = passWord;
    }
    @Generated(hash = 968487393)
    public UserProfile() {
    }
    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAvatar() {
        return this.avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getGender() {
        return this.gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPassWord() {
        return this.passWord;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

}
