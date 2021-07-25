package com.lwl.ms.playerservice.web;
import java.util.List;
import com.lwl.ms.playerservice.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lwl.ms.playerservice.service.PlayerService;

@RestController
@RequestMapping("/api/v1")
public class PlayerController {

	private PlayerService playerService;

	public PlayerController(PlayerService playerService){
		this.playerService = playerService;
	}

	@GetMapping("/{label}")
	public ResponseEntity<?> getPlayers(@PathVariable("label")String label){
		List<Player> list = playerService.getPlayersByLabel(label);
		return  ResponseEntity.ok(list);
	}

	@GetMapping("/count/{label}")
	public ResponseEntity<?> getPlayersCount(@PathVariable("label")String label){
		List<RoleCount> list = playerService.getRoleCountByLabel(label);
		return  ResponseEntity.ok(list);
	}

	@GetMapping("/amount/{label}")
	public ResponseEntity<?> getPlayersAmount(@PathVariable("label")String label){
		List<RoleAmount> list = playerService.getRoleAmountByTeam(label);
		return  ResponseEntity.ok(list);
	}

	@GetMapping("/team/amount")
	public ResponseEntity<?> getTeamsAmount(){
		List<TeamAmount> list = playerService.getAmountSpentByAllTeams();
		return  ResponseEntity.ok(list);
	}
}
