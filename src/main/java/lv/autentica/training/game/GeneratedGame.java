package lv.autentica.training.game;

import lv.autentica.training.game.config.GeneratedGameConfiguration;
import lv.autentica.training.game.domain.Gameplay;
import lv.autentica.training.game.domain.Player;
import lv.autentica.training.game.domain.Team;
import lv.autentica.training.game.domain.factory.PlayerFactory;
import lv.autentica.training.game.domain.factory.TeamFactory;
import lv.autentica.training.game.service.GameplayService;
import lv.autentica.training.game.service.PlayerService;
import lv.autentica.training.game.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class GeneratedGame implements Game {

    private final Logger log = LoggerFactory.getLogger(GeneratedGame.class);

    @Inject
    private GeneratedGameConfiguration generatedGameConfiguration;

    @Inject
    PlayerService playerService;

    @Inject
    TeamService teamService;

    @Inject
    GameplayService gameplayService;

    @Inject
    TeamFactory teamFactory;

    @Inject
    PlayerFactory playerFactory;

    public void play() {
        List<Team> teams = teamService.findAllTeams();
        for (int i = 0; i < teams.size(); i++) {
            for (int j = i + 1; j < teams.size(); j++) {
                Gameplay gameplay = new Gameplay();

                Team teamA = teams.get(i);
                Team teamB = teams.get(j);

                teamA.getGameplays().add(gameplay);
                teamB.getGameplays().add(gameplay);

                gameplay.setHomeTeam(teamA);
                gameplay.setGuestTeam(teamB);

                Double teamAOverall = teamService.getAverageOverall(teamA);
                Double teamBOverall = teamService.getAverageOverall(teamB);

                if (teamAOverall > teamBOverall) {
                    gameplayService.addPointsToHomeTeam(gameplay, 2);
                } else if (teamAOverall < teamBOverall) {
                    gameplayService.addPointsToGuestTeam(gameplay, 2);
                } else {
                    gameplayService.addPointsToHomeTeam(gameplay, 1);
                    gameplayService.addPointsToGuestTeam(gameplay, 1);
                }

                gameplayService.saveGameplay(gameplay);
            }
        }
    }

    public void generate() {
        IntStream
                .iterate(1, i -> i + 1)
                .limit(this.generatedGameConfiguration.getTeamCount())
                .forEach((i) -> teamService.save(teamFactory.construct()));

        for (Team team : teamService.findAllTeams()) {
            IntStream
                    .iterate(1, i -> i + 1)
                    .limit(this.generatedGameConfiguration.getPlayerCountPerTeam())
                    .forEach((i) ->
                            {
                                Player player = playerFactory.construct();
                                player.setTeam(team);

                                playerService.savePlayer(player);

                                team.getPlayers().add(player);
                            }
                    );
            teamService.save(team);
        }
    }


    @Override
    public Map<String, Integer> getResults() {
        Comparator<Integer> valueComparator = (e1, e2) -> e2.compareTo(e1);

        Map<String, Integer> scoreboard = new LinkedHashMap<>();

        for (Team team : this.getTeams()) {
            Integer totalScore = 0;
            totalScore += gameplayService.findAllGameplaysByHomeTeam(team).stream()
                    .mapToInt(Gameplay::getHomeTeamPoints)
                    .sum();
            totalScore += gameplayService.findAllGameplaysByGuestTeam(team).stream()
                    .mapToInt(Gameplay::getGuestTeamPoints)
                    .sum();
            scoreboard.put(team.getName(), totalScore);
        }

        return scoreboard.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(valueComparator))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
    }

    @Override
    public List<Team> getTeams() {
        return teamService.findAllTeams();
    }

    @PostConstruct
    public void setup() {
        this.generate();
    }
}
