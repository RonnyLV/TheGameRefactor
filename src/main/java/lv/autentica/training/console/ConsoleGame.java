package lv.autentica.training.console;

import lv.autentica.training.annotation.ConsoleController;
import lv.autentica.training.game.Game;
import lv.autentica.training.game.domain.Player;
import lv.autentica.training.game.domain.Team;
import lv.autentica.training.game.service.PlayerService;
import lv.autentica.training.game.service.TeamService;
import org.codehaus.plexus.components.interactivity.OutputHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Map;

@ConsoleController
public class ConsoleGame implements ConsoleApplication {

    private final Logger log = LoggerFactory.getLogger(ConsoleGame.class);

    @Inject
    private Game game;

    @Inject
    TeamService teamService;

    @Inject
    PlayerService playerService;

    @Inject
    OutputHandler outputHandler;

    private void printTeamInfo() {
        try {
            outputHandler.writeLine("=== Teams info ===");

            for (Team team : this.game.getTeams()) {
                outputHandler.writeLine(
                        String.format(
                                "== Info for team: %s (%s)",
                                team.getName(),
                                teamService.getAverageOverall(team)
                        )
                );
                for (Player player : team.getPlayers()) {
                    outputHandler.writeLine(
                            String.format(
                                    "\t%s (%s)",
                                    playerService.getFullName(player),
                                    player.getOverall()
                            )
                    );
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public void printResults() {
        try {
            outputHandler.writeLine("=== Result table ===");
            for (Map.Entry<String, Integer> result : this.game.getResults().entrySet()) {
                outputHandler.writeLine(result.getKey() + ": " + result.getValue());
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void play() {
        try {
            outputHandler.writeLine("=== Games started ===");
            this.game.play();
            outputHandler.writeLine("=== Games ended ===");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void start() {
        this.printTeamInfo();
        this.play();
        this.printResults();
    }
}
