package lv.autentica.training.game.repository;

import lv.autentica.training.game.domain.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, String> {
    List<Team> findByName(String name);

    List<Team> findAll();
}