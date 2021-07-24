package com.lwl.ms.playerservice.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TotalAndRoleAmount {

	private String label;
	private double totalSum;
	private List<RoleAmountCount> roleWise = new ArrayList<>();
}
