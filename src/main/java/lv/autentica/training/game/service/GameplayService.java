package lv.autentica.training.game.service;

import lv.autentica.training.game.domain.Gameplay;
import lv.autentica.training.game.domain.Team;
import lv.autentica.training.game.repository.GameplayRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class GameplayService {

    @Inject
    GameplayRepository gameplayRepository;

    @Inject
    TeamService teamService;

    public void addPointsToHomeTeam(Gameplay gameplay, Integer additionalPoints) {
        if (gameplay.getHomeTeamPoints() == null) {
            gameplay.setHomeTeamPoints(additionalPoints);
        } else {
            gameplay.setHomeTeamPoints(gameplay.getHomeTeamPoints() + additionalPoints);
        }
    }

    public void addPointsToGuestTeam(Gameplay gameplay, Integer additionalPoints) {
        if (gameplay.getGuestTeamPoints() == null) {
            gameplay.setGuestTeamPoints(additionalPoints);
        } else {
            gameplay.setGuestTeamPoints(gameplay.getGuestTeamPoints() + additionalPoints);
        }
    }

    public Integer getWinner(Gameplay gameplay) {
        return gameplay.getHomeTeamPoints().compareTo(gameplay.getGuestTeamPoints());
    }

    public void saveGameplay(Gameplay gameplay) {
        gameplayRepository.save(gameplay);
    }

    public List<Gameplay> findAllGameplays() {
        return gameplayRepository.findAll();
    }

    public List<Gameplay> findAllGameplaysByHomeTeam(Team team) {
        return gameplayRepository.findAllByHomeTeam(team);
    }

    public List<Gameplay> findAllGameplaysByGuestTeam(Team team) {
        return gameplayRepository.findAllByGuestTeam(team);
    }
}
