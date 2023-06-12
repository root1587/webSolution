package com.root.biz.dto;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class refreshTokenDto {

    private String token;
    
    private String ip;
    
    private Collection authorities;
    
    private String refreshToken;
}
