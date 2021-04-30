package io.cricket.ipldashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.cricket.ipldashboard.entity.Team;
import io.cricket.ipldashboard.repository.MatchRepository;
import io.cricket.ipldashboard.repository.TeamRepository;

@RestController
public class TeamController {

	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private MatchRepository matchRepository;

	@GetMapping("/team/{teamName}")
	public Team getTeam(@PathVariable String teamName) {
		
		Team team = this.teamRepository.findByTeamName(teamName);
		team.setMatches(matchRepository.getLatestMatchesByTeam(teamName, 4));
		
		return team;
	}

}
