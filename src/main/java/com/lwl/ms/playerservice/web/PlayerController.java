package com.lwl.ms.playerservice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lwl.ms.playerservice.domain.APIErrorResponse;
import com.lwl.ms.playerservice.domain.Player;
import com.lwl.ms.playerservice.domain.RoleAmountCount;
import com.lwl.ms.playerservice.domain.TeamAmount;
import com.lwl.ms.playerservice.domain.TotalAndRoleAmount;
import com.lwl.ms.playerservice.service.PlayerService;

@RestController
@RequestMapping("/api")
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	@GetMapping("/")
	public ResponseEntity<?> getAllPlayers() {
		return ResponseEntity.ok(playerService.getAllPlayers());
	}

	@GetMapping("/{name}")
	public ResponseEntity<?> searchPlayers(@PathVariable String name) {
		List<Player> list = playerService.searchPlayer(name);
		if (list.isEmpty()) {
			String errMsg = "No Player found with name: '" + name + "'";
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(APIErrorResponse.builder().errorMsg(errMsg).build());
		}
		return ResponseEntity.ok(list);
	}

	@GetMapping("/label/{label}")
	public ResponseEntity<?> getPlayersByLabel(@PathVariable String label) {
		List<Player> list = playerService.getPlayersByLabel(label);
		if (list.isEmpty()) {
			String errMsg = "No Team found with label: '" + label + "'";
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(APIErrorResponse.builder().errorMsg(errMsg).build());
		}
		return ResponseEntity.ok(list);
	}

	@GetMapping("/rolecount/{label}")
	public ResponseEntity<?> getRoleAndAmountByLabel(@PathVariable String label) {
		List<RoleAmountCount> list = playerService.getRoleAndAmountByLabel(label);
		if (list.isEmpty()) {
			String errMsg = "No Team found with label: '" + label + "'";
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(APIErrorResponse.builder().errorMsg(errMsg).build());
		}
		return ResponseEntity.ok(list);
	}

	@GetMapping("/team")
	public ResponseEntity<?> getTeamsSpending(
			@RequestParam(name = "roleWise", defaultValue = "N", required = false) String roleWise) {
		if (roleWise.equalsIgnoreCase("Y")) {
			System.out.println("In Y");
			List<TotalAndRoleAmount> list = playerService.getRoleAndAmount();
			return ResponseEntity.ok(list);
		}
		List<TeamAmount> teamAmount = playerService.getTeamAmount();
		return ResponseEntity.ok(teamAmount);
	}

}
