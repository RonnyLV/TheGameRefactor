package lv.autentica.training.game.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;

import java.util.ArrayList;
import java.util.List;

@KeySpace
public class Team {

    @Id
    String uuid;

    private String name;

    private List<Gameplay> gameplays = new ArrayList<>();

    private List<Player> players = new ArrayList<>();

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Gameplay> getGameplays() {
        return gameplays;
    }

    public void setGameplays(List<Gameplay> gameplays) {
        this.gameplays = gameplays;
    }
}
