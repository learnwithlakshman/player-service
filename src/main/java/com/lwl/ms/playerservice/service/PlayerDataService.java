package com.lwl.ms.playerservice.service;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import com.lwl.ms.playerservice.domain.Player;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PlayerDataService {

	private static final List<Player> PLAYERS_LIST = new ArrayList<>();
	private static final Set<String> LABELS = new HashSet<>();
	private static final Set<String> ROLES = new HashSet<>();
	private static final String SEP = ",";
	private static final String fileName="players.csv";

	@PostConstruct
	public void init() {
		try {
			log.debug("Reading data from {} file",fileName);
			List<String> allLines = Files.readAllLines(new ClassPathResource(fileName).getFile().toPath());
			log.debug("File {} has {} total players",fileName,allLines.size());
			processData(allLines);
			log.info("File {} has total players {} and processed successfully",fileName, PLAYERS_LIST.size());
			int diff = allLines.size() - PLAYERS_LIST.size();
			if (diff == 0) {
				log.debug("All Players info processed");
			} else {
				log.debug("Processed {} out of {} players info", diff, allLines.size());
			}
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}
	public List<Player> getPlayers() {
		return PLAYERS_LIST;
	}
	public Set<String> getRoles() {
		return ROLES;
	}
	public Set<String> getLabels() {
		return LABELS;
	}
	public boolean isValidRole(String role) {
		return ROLES.contains(role);
	}
	public boolean isValidLabel(String label) {
		return LABELS.contains(label);
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
		int count = 0;
		String name = playerArr[count++];
		double amount = Double.parseDouble(playerArr[count++]);
		String role = playerArr[count++];
		String label = playerArr[count++];
		LABELS.add(label);
		ROLES.add(role);
		return Player.builder().name(name).amount(amount).role(role).label(label).build();
	}
}
