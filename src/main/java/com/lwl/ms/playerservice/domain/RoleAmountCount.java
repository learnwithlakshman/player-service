package com.lwl.ms.playerservice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class RoleAmountCount {

	private String role;
	private int count;
	private double amount;
}
