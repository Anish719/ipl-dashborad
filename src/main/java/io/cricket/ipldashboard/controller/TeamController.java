package io.cricket.ipldashboard.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.cricket.ipldashboard.entity.Match;
import io.cricket.ipldashboard.entity.Team;
import io.cricket.ipldashboard.repository.MatchRepository;
import io.cricket.ipldashboard.repository.TeamRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
	
	@GetMapping("/team/{teamName}/matches")
	public List<Match> getTeamMatchesByYear(@PathVariable String teamName, @RequestParam int year) {

		LocalDate startDate = LocalDate.of(year, 1, 1);
		LocalDate endDate = LocalDate.of(year + 1, 1, 1);

		return this.matchRepository.getTeamMatchesByYear(teamName, startDate, endDate);

	}

}
