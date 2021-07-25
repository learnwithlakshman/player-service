package com.lwl.ms.playerservice.service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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

	private final PlayerDataService playerDataService;
	private final List<Player> players;

	public PlayerServiceImpl(PlayerDataService playerDataService){
		this.playerDataService = playerDataService;
		this.players = playerDataService.getPlayers();
    }

	@Override
	public List<Player> getPlayersByLabel(String label) {
		Assert.hasLength(label, "Label can't be is empty or null");
		log.info("Looking for players of {}",label);
		List<Player> searchList = players.stream().filter(p->p.getLabel().equalsIgnoreCase(label))
				                  .collect(Collectors.toList());
		log.info("Total {} Players for label {}", searchList.size(), label);
		return searchList;
	}

	@Override
	public List<RoleCount> getRoleCountByLabel(String label) {
		Assert.hasLength(label, "Label can't be is empty or null");
		List<RoleCount> roleCounts = new ArrayList<>();
		log.info("Looking for label {} role count",label);
		players.stream().filter(p->p.getLabel().equalsIgnoreCase(label))
				.collect(Collectors.groupingBy(Player::getRole)).forEach((k,v)->{
			log.info("Team {} and role {} has {} players",label,k,v.size());
			int count = v.size();
			RoleCount roleCount = RoleCount.builder().count(count).role(k).build();
			roleCounts.add(roleCount);
		});
		return roleCounts;

	}

	@Override
	public List<TeamAmount> getAmountSpentByAllTeams() {
		List<TeamAmount> teamAmounts = new ArrayList<>();
		players.stream().collect(Collectors.groupingBy(Player::getLabel)).forEach((key,value)->{
				log.info("Team {} has {} players",key,value.size());
				double amount = value.stream().mapToDouble(Player::getAmount).sum();
				int count = value.size();
				String label = key;
				TeamAmount teamAmount = TeamAmount.builder().label(label).count(count).amount(amount).build();
				teamAmounts.add(teamAmount);
		});
		return teamAmounts;
	}

	@Override
	public List<RoleAmount> getRoleAmountByTeam(String label) {
		Assert.hasLength(label, "Label can't be is empty or null");
		List<RoleAmount> roleAmounts = new ArrayList<>();
		log.info("Looking for label {} role amount",label);
		players.stream().filter(p->p.getLabel().equalsIgnoreCase(label))
				.collect(Collectors.groupingBy(Player::getRole)).forEach((k,v)->{
					log.info("Team {} and role {} has {} players",label,k,v.size());
					double amount = v.stream().mapToDouble(Player::getAmount).sum();
					RoleAmount roleAmount = RoleAmount.builder().name(k).amount(amount).build();
					roleAmounts.add(roleAmount);
		});
		return roleAmounts;
	}

}
