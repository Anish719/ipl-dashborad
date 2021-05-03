package io.cricket.ipldashboard.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import io.cricket.ipldashboard.entity.Match;

public interface MatchRepository extends CrudRepository<Match, Long> {
	
	List<Match> getByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2, Pageable pageable);
	
	@Query("SELECT m from Match m WHERE (team1 = :teamName OR team2 = :teamName) AND date BETWEEN :startDate AND :endDate ORDER BY date DESC")
	List<Match> getTeamMatchesByYear(
			@Param("teamName") String teamName, 
			@Param("startDate") LocalDate startDate, 
			@Param("endDate") LocalDate endDate);
	
	List<Match> getByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(
			String teamName1, LocalDate date1, LocalDate date2,
			String teamName2, LocalDate date3, LocalDate date4);
	
	default List<Match> getLatestMatchesByTeam(String teamName, int count) {
		return getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, PageRequest.of(0, count));
	}

}
