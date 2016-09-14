package lv.autentica.training.game.domain.factory;

import lv.autentica.training.game.domain.Player;
import org.springframework.stereotype.Service;

@Service
public class PlayerFactory extends Factory<Player> {

    private static String[] firstNames = new String[]{"Juris", "Janis", "Ilja", "Arturs", "Toms", "Ivars", "Sandis"};
    private static String[] lastNames = new String[]{"Abols", "Zirnis", "Kalns", "Semjonovs", "Rumba", "Vagars", "Poga"};

    @Override
    public Player construct() {
        Player player = new Player();
        player.setFirstName(this.getRandomStringFromArray(firstNames));
        player.setLastName(this.getRandomStringFromArray(lastNames));
        player.setOverall(this.randInt(60, 100));
        return player;
    }
}
