package com.lwl.ms.playerservice.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.lwl.ms.playerservice.domain.Player;
import com.lwl.ms.playerservice.domain.RoleAmountCount;
import com.lwl.ms.playerservice.domain.TeamAmount;
import com.lwl.ms.playerservice.domain.TotalAndRoleAmount;

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
	public List<RoleAmountCount> getRoleAndAmountByLabel(String label) {
		Assert.hasLength(label, "label is empty");
		List<RoleAmountCount> roleCountList = new ArrayList<>();
		log.info("Computing Role wise Amount for label {}", label);
		List<Player> players = getPlayersByLabel(label);
		Set<String> roles = playerDataService.getRoles();
		Iterator<String> it = roles.iterator();
		int count = 0;
		double amount = 0;
		while (it.hasNext()) {
			String role = it.next();
			count = 0;
			amount = 0;
			for (Player p : players) {
				if (p.getRole().equals(role)) {
					count++;
					amount = Double.sum(amount, p.getAmount());
				}
			}
			roleCountList.add(RoleAmountCount.builder().count(count).role(role).amount(amount).build());
		}
		log.info("Computed Role wise Amount for label {}", label);
		return roleCountList;
	}

	@Override
	public List<TeamAmount> getTeamAmount() {
		List<TeamAmount> list = new ArrayList<>();
		Set<String> labels = playerDataService.getLabels();
		log.info("Computing total amount for {} teams", labels.size());
		Iterator<String> it = labels.iterator();
		while (it.hasNext()) {
			String label = it.next();
			List<Player> players = getPlayersByLabel(label);
			double amount = 0;
			for (Player p : players) {
				amount = Double.sum(amount, p.getAmount());
			}
			list.add(TeamAmount.builder().amount(amount).label(label).build());
		}
		return list;
	}

	@Override
	public List<TotalAndRoleAmount> getRoleAndAmount() {
		List<TotalAndRoleAmount> list = new ArrayList<>();
		Set<String> labels = playerDataService.getLabels();
		log.info("Computing Total and role amount for {} teams", labels.size());
		Iterator<String> it = labels.iterator();
		while (it.hasNext()) {
			String label = it.next();
			TotalAndRoleAmount totalAndRoleAmount = computeRoleAndAmount(label);
			list.add(totalAndRoleAmount);
		}
		log.info("Computed for {} teams", list.size());
		return list;
	}

	private TotalAndRoleAmount computeRoleAndAmount(String label) {
		log.info("Computing Total and role wise amount for Team: {}", label);
		List<Player> players = getPlayersByLabel(label);

		Set<String> roles = playerDataService.getRoles();

		List<RoleAmountCount> roleWiseList = new ArrayList<>();

		Iterator<String> it = roles.iterator();
		int count = 0;
		double roleAmount = 0;
		double totalAmount = 0;
		while (it.hasNext()) {
			String role = it.next();
			count = 0;
			roleAmount = 0;
			for (Player p : players) {
				if (p.getRole().equals(role)) {
					count++;
					roleAmount = Double.sum(roleAmount, p.getAmount());
				}
			}
			roleWiseList.add(RoleAmountCount.builder().count(count).role(role).amount(roleAmount).build());
			totalAmount = Double.sum(totalAmount, roleAmount);
		}
		return new TotalAndRoleAmount(label, totalAmount, roleWiseList);
	}

}
