package com.lwl.ms.playerservice.domain;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleCount {
	private String role;
	private int count;
}
