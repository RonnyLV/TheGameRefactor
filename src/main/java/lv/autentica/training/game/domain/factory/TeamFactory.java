package lv.autentica.training.game.domain.factory;

import lv.autentica.training.game.domain.Team;
import org.springframework.stereotype.Service;

@Service
public class TeamFactory extends Factory<Team> {

    private static String[] region
            = new String[]{"Rīgas", "Liepājas", "Daugavpils", "Jelgavas", "Saulkrastu", "Ventspils", "Valmieras"};
    private static String[] signature
            = new String[]{"Eži", "Vilki", "Raķetes", "Degunradži", "Lodes", "Saule", "Ogļrači"};

    @Override
    public Team construct() {
        Team team = new Team();

        team.setName(
                String.format(
                        "%s %s",
                        this.getRandomStringFromArray(region),
                        this.getRandomStringFromArray(signature)
                )
        );

        return team;
    }
}
