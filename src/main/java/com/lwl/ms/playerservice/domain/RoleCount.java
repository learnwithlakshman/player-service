package com.lwl.ms.playerservice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class RoleCount {

	private String role;
	private int count;
}
