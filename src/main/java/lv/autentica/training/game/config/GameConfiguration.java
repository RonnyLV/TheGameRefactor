package lv.autentica.training.game.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.map.repository.config.EnableMapRepositories;

@Configuration
@EnableMapRepositories("lv.autentica.training.game.repository")
public class GameConfiguration {

}
