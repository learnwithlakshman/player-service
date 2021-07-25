package com.lwl.ms.playerservice.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Player {
	private String name;
	private String role;
	private double amount;
	private String label;
}