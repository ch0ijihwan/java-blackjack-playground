package controller;

import model.card.Deck;
import model.participant.Dealer;
import model.participant.Player;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static view.input.InputDisplay.*;
import static view.output.OutputDisplay.*;

public class Controller {
    private final Deck deck = new Deck();
    private final Dealer dealer = new Dealer(deck.startingDraw());

    public Controller() {
        showMessageAskNames();
        String[] inputtedPlayerNames = inputPlayerNames();
        List<Player> players = setUpPlayers(inputtedPlayerNames);
        showMessageStartingDraw(getPlayerNames(players));
        showDealerOneCard(dealer.getOneCardStatus());
        showAllPlayersCardStatus(players);
        drawAllPlayer(players);
        dealerCanDraw();
        showAllResultOfBattles(players);
        showAllResultOfDividends(players);
    }

    private List<Player> setUpPlayers(final String[] inputtedPlayerNames) {
        return Arrays.stream(inputtedPlayerNames)
                .map(name -> new Player(name, inputBattlingMoney(name), deck.startingDraw()))
                .collect(Collectors.toUnmodifiableList());
    }

    private List<String> getPlayerNames(final List<Player> players) {
        return players.stream()
                .map(Player::getNameValue)
                .collect(Collectors.toUnmodifiableList());
    }

    private void showAllPlayersCardStatus(final List<Player> players) {
        players.forEach(player -> showPlayerCardStatus(player.getNameValue(), player.getCards().toString()));
    }

    private void drawAllPlayer(List<Player> players) {
        players.forEach(this::drawOrStay);
    }

    private void drawOrStay(Player player) {
        while (!player.isStay()) {
            boolean haveToDraw = inputDrawMoreCard(player.getNameValue());
            if (haveToDraw) {
                player.draw(deck.draw());
                showPlayerCardStatus(player.getNameValue(), player.getCards().toString());
            } else if (!haveToDraw) {
                player.stay();
            }
        }
    }

    private void dealerCanDraw() {
        if (dealer.isScoreLessThan16()) {
            dealer.draw(deck.draw());
            showMessageDealerWasDrawing();
        }
    }

    private void showAllResultOfBattles(final List<Player> players) {
        showCardsResult("딜러", dealer.getCards().toString(), dealer.getScore());
        players.forEach(player -> showCardsResult(player.getNameValue(), player.getCards().toString(), player.getCards().getScore()));
    }

    private void showAllResultOfDividends(final List<Player> players) {
        players.forEach(player -> showResultOfDividends(player.getNameValue(), player.battle(dealer.getCards())));
        int dealersDividends = players.stream()
                .mapToInt(player -> player.battle(dealer.getCards())).sum() * -1;
        showResultOfDealerDividends(dealersDividends);
    }
}