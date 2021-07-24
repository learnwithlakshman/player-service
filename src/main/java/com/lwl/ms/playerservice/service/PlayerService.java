package com.lwl.ms.playerservice.service;

import java.util.List;

import com.lwl.ms.playerservice.domain.Player;
import com.lwl.ms.playerservice.domain.RoleAmountCount;
import com.lwl.ms.playerservice.domain.TeamAmount;
import com.lwl.ms.playerservice.domain.TotalAndRoleAmount;

public interface PlayerService {

	List<Player> getAllPlayers();

	List<Player> searchPlayer(String name);

	List<Player> getPlayersByLabel(String label);

	List<Player> getPlayersByRole(String role);

	List<Player> getPlayersByLabelAndRole(String label, String role);

	List<RoleAmountCount> getRoleAndAmountByLabel(String label);

	List<TeamAmount> getTeamAmount();

	List<TotalAndRoleAmount> getRoleAndAmount();

}
