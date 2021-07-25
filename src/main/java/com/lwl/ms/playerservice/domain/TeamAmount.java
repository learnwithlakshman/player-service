package com.lwl.ms.playerservice.domain;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamAmount {
	private String label;
	private int count;
	private double amount;
}
