package lv.autentica.training.game.service;

import lv.autentica.training.game.domain.Player;
import lv.autentica.training.game.domain.Team;
import lv.autentica.training.game.repository.TeamRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class TeamService {

    @Inject
    TeamRepository teamRepository;

    public Double getAverageOverall(Team team) {
        return team.getPlayers()
                .stream()
                .mapToInt(Player::getOverall)
                .average()
                .getAsDouble();
    }

    public List<Team> findAllTeams() {
        return teamRepository.findAll();
    }

    public void save(Team team) {
        teamRepository.save(team);
    }

}
