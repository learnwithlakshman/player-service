package com.lwl.ms.playerservice.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.lwl.ms.playerservice.domain.Player;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PlayerDataService {

	private static final List<Player> PLAYERS_LIST = new ArrayList<>();

	private static final String SEP = ",";

	@PostConstruct
	public void init() {
		try {
			log.info("Reading Data from CSV file");
			File file = new ClassPathResource("players.csv").getFile();
			List<String> allLines = Files.readAllLines(file.toPath());
			log.info("Processing Players info, Found no of players count: {} from CSV file", allLines.size());
			processData(allLines);
			log.info("Players info processed count: {}", PLAYERS_LIST.size());
			int diff = allLines.size() - PLAYERS_LIST.size();
			if (diff == 0) {
				log.info("All Players info processed");
			} else {
				log.debug("Processed {} out of {} players info", diff, allLines.size());
			}
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	private void processData(List<String> allLines) {
		for (String s : allLines) {
			try {
				Player player = getPlayerObj(s);
				PLAYERS_LIST.add(player);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
	}

	private Player getPlayerObj(String s) {
		String[] playerArr = s.split(SEP);
		String name = playerArr[0];
		String amount = playerArr[1];
		String role = playerArr[2];
		String label = playerArr[3];
		return new Player(name, role, Double.valueOf(amount), label);
	}

	public List<Player> getPlayers() {
		return PLAYERS_LIST;
	}

}
