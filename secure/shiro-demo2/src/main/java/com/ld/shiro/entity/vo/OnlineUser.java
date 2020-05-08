package com.ld.shiro.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author ld
 * @Date 2020/4/27 12:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OnlineUser implements Serializable {

    private String userId;

    private String username;

    private String ip;

    private LocalDateTime loginTime;
}
