package lv.autentica.training.config.game;

import lv.autentica.training.game.config.GeneratedGameConfiguration;
import org.codehaus.plexus.components.interactivity.Prompter;
import org.codehaus.plexus.components.interactivity.PrompterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Configuration
public class GeneratedGameConsoleConfiguration implements GeneratedGameConfiguration {

    private final Logger log = LoggerFactory.getLogger(GeneratedGameConsoleConfiguration.class);

    @Inject
    Prompter prompter;

    private Long teamCount;

    private Long playerCountPerTeam;

    @Override
    public Long getTeamCount() {
        return this.teamCount;
    }

    @Override
    public Long getPlayerCountPerTeam() {
        return this.playerCountPerTeam;
    }

    @PostConstruct
    private void setup() {
        try {
            this.teamCount = Long.valueOf(prompter.prompt("Please enter team count"));
        } catch (PrompterException e) {
            log.error(e.getMessage());
        }

        if (this.teamCount == null || this.teamCount < 2) {
            this.teamCount = 5L;
        }

        this.playerCountPerTeam = 2L;
    }
}
