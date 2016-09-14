package lv.autentica.training.game;

import lv.autentica.training.game.domain.Team;

import java.util.List;
import java.util.Map;

public interface Game {

    void play();

    Map<String, Integer> getResults();

    List<Team> getTeams();

}
