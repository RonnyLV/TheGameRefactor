package lv.autentica.training.game.service;

import lv.autentica.training.game.domain.Player;
import lv.autentica.training.game.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class PlayerService {
    @Inject
    PlayerRepository playerRepository;

    public String getFullName(Player player) {
        return String.format("%s %s", player.getFirstName(), player.getLastName());
    }

    public void savePlayer(Player player) {
        playerRepository.save(player);
    }
}
