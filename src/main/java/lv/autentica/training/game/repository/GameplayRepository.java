package lv.autentica.training.game.repository;

import lv.autentica.training.game.domain.Gameplay;
import lv.autentica.training.game.domain.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GameplayRepository extends CrudRepository<Gameplay, String> {
    List<Gameplay> findAll();

    List<Gameplay> findAllByHomeTeam(Team homeTeam);

    List<Gameplay> findAllByGuestTeam(Team homeTeam);
}