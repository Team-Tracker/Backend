package io.github.teamtracker.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import io.github.teamtracker.model.team.Member;

public interface MemberRepository extends CrudRepository<Member, Integer> {

    @Query(value = "SELECT m.* FROM member m WHERE m.user_id = ?1 AND m.team_id = ?2", nativeQuery = true)
    public Member findByUserIdAndTeamId(Integer userId, Integer teamId);
}