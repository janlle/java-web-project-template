package com.domain.dao.entity;

import com.domain.common.IdEntity;

import javax.persistence.*;

/**
 * <p>
 *
 * @author leone
 * @since 1.8
 **/
@Entity
public class User extends IdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(insertable = false, updatable = false, columnDefinition = "INT UNSIGNED COMMENT '用户id'")
    private Integer userId;

    @Column(length = 50, columnDefinition = "VARCHAR(255) NOT NULL COMMENT '昵称'")
    private String nickname;

    @Column(columnDefinition = "BOOLEAN NOT NULL COMMENT '是否已被禁用。false：未禁用(默认)，true：已禁用'")
    private Boolean disabled = false;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }
}
