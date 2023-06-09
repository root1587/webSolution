package com.root.biz.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminLoginVo {
	private String id;
	private String pw;
	private String name;
	private String authKey;
}
