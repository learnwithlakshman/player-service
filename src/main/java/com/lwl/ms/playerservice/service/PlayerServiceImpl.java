package com.lwl.ms.playerservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
		
		/*
		 * if (!playerDataService.isValidLabel(label)) { throw new
		 * IllegalArgumentException("Invalid label : '" + label + "'"); }
		 */
		
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
		Assert.hasLength(role, "role is empty");

		/*
		 * if (!playerDataService.isValidRole(role)) { throw new
		 * IllegalArgumentException("Invalid role : '" + role + "'"); }
		 */
		List<Player> players = playerDataService.getPlayers();
		List<Player> searchList = new ArrayList<>();
		players.parallelStream().forEach(p -> {
			if (p.getRole().equals(role)) {
				searchList.add(p);
			}
		});
		log.info("Found {} Players for role {}", searchList.size(), role);
		return searchList;
	}

	@Override
	public List<Player> getPlayersByLabelAndRole(String label, String role) {
		Assert.hasLength(label, "label is empty");
		Assert.hasLength(role, "role is empty");

		/*
		 * if (!(playerDataService.isValidLabel(label) &&
		 * playerDataService.isValidRole(role))) { String err = "Invalid label or role";
		 * throw new IllegalArgumentException(err); }
		 */

		List<Player> players = playerDataService.getPlayers();
		List<Player> searchList = new ArrayList<>();
		players.parallelStream().forEach(p -> {
			if (p.getLabel().equals(label) && p.getRole().equals(role)) {
				searchList.add(p);
			}
		});
		log.info("Found {} Players for label {} and role {}", searchList.size(), label, role);
		return searchList;
	}

	@Override
	public List<RoleCount> getRoleCountByLabel(String label) {
		Assert.hasLength(label, "label is empty");
		List<RoleCount> roleCountList = new ArrayList<>();
		List<Player> players = getPlayersByLabel(label);
		Set<String> roles = playerDataService.getRoles();
		int count = 0;
		roles.forEach(r -> {
			players.parallelStream().forEach(p -> {
				if (p.getRole().equals(r)) {
					// Need to work here
				}
			});
		});

		return roleCountList;
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
