package com.lwl.ms.playerservice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lwl.ms.playerservice.domain.APIErrorResponse;
import com.lwl.ms.playerservice.domain.Player;
import com.lwl.ms.playerservice.service.PlayerService;

@RestController
@RequestMapping("/api/v1/player")
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	@GetMapping()
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

}
