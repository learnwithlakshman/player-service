package com.lwl.ms.playerservice.service;
import java.util.List;
import com.lwl.ms.playerservice.domain.Player;
import com.lwl.ms.playerservice.domain.RoleAmount;
import com.lwl.ms.playerservice.domain.RoleCount;
import com.lwl.ms.playerservice.domain.TeamAmount;

public interface PlayerService {
	List<Player> getPlayersByLabel(String label);
	List<RoleCount> getRoleCountByLabel(String label);
	List<TeamAmount> getAmountSpentByAllTeams();
	List<RoleAmount> getRoleAmountByTeam(String label);

}
