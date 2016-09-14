package lv.autentica.training.game.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;

@KeySpace
public class Gameplay {

    @Id
    private String uuid;

    private Team homeTeam;

    private Team guestTeam;

    private Integer homeTeamPoints = 0;

    private Integer guestTeamPoints = 0;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getGuestTeam() {
        return guestTeam;
    }

    public void setGuestTeam(Team guestTeam) {
        this.guestTeam = guestTeam;
    }

    public Integer getHomeTeamPoints() {
        return homeTeamPoints;
    }

    public void setHomeTeamPoints(Integer homeTeamPoints) {
        this.homeTeamPoints = homeTeamPoints;
    }

    public Integer getGuestTeamPoints() {
        return guestTeamPoints;
    }

    public void setGuestTeamPoints(Integer guestTeamPoints) {
        this.guestTeamPoints = guestTeamPoints;
    }
}
