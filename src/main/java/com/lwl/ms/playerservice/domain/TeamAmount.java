package com.lwl.ms.playerservice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TeamAmount {

	private double amount;
	private String label;

}
