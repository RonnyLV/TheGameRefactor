package lv.autentica.training.game.repository;

import lv.autentica.training.game.domain.Player;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlayerRepository extends CrudRepository<Player, String> {
    List<String> findByLastName(String lastName);
}