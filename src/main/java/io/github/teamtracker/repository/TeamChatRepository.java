package io.github.teamtracker.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import io.github.teamtracker.model.team.TeamChat;

public interface TeamChatRepository extends CrudRepository<TeamChat, Integer> {

    @Query(value = "SELECT tc.* FROM team_chat tc WHERE tc.team_id = ?1", nativeQuery = true)
    public TeamChat findByTeamId(Integer teamId);
}