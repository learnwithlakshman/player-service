package com.lwl.ms.playerservice.service;

import java.util.List;

import com.lwl.ms.playerservice.domain.Player;
import com.lwl.ms.playerservice.domain.RoleAmount;
import com.lwl.ms.playerservice.domain.RoleCount;
import com.lwl.ms.playerservice.domain.TeamAmount;

public interface PlayerService {

	List<Player> getAllPlayers();

	List<Player> searchPlayer(String name);

	List<Player> getPlayersByLabel(String label);

	List<Player> getPlayersByRole(String role);

	List<Player> getPlayersByLabelAndRole(String label, String role);

	List<RoleCount> getRoleCountByLabel(String label);

	List<TeamAmount> getTeamAmount();

	List<RoleAmount> getRoleAmountByTeam(String label);

}
