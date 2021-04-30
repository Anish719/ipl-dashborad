package io.cricket.ipldashboard.repository;

import org.springframework.data.repository.CrudRepository;

import io.cricket.ipldashboard.entity.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {

	Team findByTeamName(String TeamName);

}
