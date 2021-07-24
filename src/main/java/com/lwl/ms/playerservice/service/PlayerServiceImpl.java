package com.lwl.ms.playerservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.lwl.ms.playerservice.domain.Player;
import com.lwl.ms.playerservice.domain.RoleAmount;
import com.lwl.ms.playerservice.domain.RoleCount;
import com.lwl.ms.playerservice.domain.TeamAmount;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerDataService playerDataService;

	@Override
	public List<Player> getAllPlayers() {
		log.info("Retrieving all players info.....");
		return playerDataService.getPlayers();
	}

	@Override
	public List<Player> searchPlayer(String name) {
		Assert.hasLength(name, "name is empty");
		String n = name.toLowerCase();
		List<Player> players = playerDataService.getPlayers();
		List<Player> searchList = new ArrayList<>();
		players.parallelStream().forEach(p -> {
			if (p.getName().toLowerCase().contains(n)) {
				searchList.add(p);
			}
		});
		log.info("Found {} Players for name {}", searchList.size(), name);
		return searchList;
	}

	@Override
	public List<Player> getPlayersByLabel(String label) {
		Assert.hasLength(label, "label is empty");
		List<Player> players = playerDataService.getPlayers();
		List<Player> searchList = new ArrayList<>();
		players.parallelStream().forEach(p -> {
			if (p.getLabel().equals(label)) {
				searchList.add(p);
			}
		});
		log.info("Found {} Players for label {}", searchList.size(), label);
		return searchList;
	}

	@Override
	public List<Player> getPlayersByRole(String role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Player> getPlayersByLabelAndRole(String label, String role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoleCount> getRoleCountByLabel(String label) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TeamAmount> getTeamAmount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoleAmount> getRoleAmountByTeam(String label) {
		// TODO Auto-generated method stub
		return null;
	}

}
