package com.aleks.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GameJson {
    @SerializedName("Player")
    private final List<Player> players = new ArrayList<>();
    @SerializedName("Steps")
    private final List<StepJson> steps = new ArrayList<>();
    @SerializedName("GameResultWinner")
    private Player winner;

    public List<Player> getPlayers() {
        return players;
    }

    public List<StepJson> getSteps() {
        return steps;
    }

    public Player getWinner() {
        return winner;
    }

    public void setPlayers(Player player) {
        players.add(player);
    }

    public void setSteps(StepJson step) {
        steps.add(step);
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
}
