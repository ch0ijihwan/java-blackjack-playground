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
    private int dealersDividends;

    public Controller() {
        showMessageAskNames();
        String[] inputtedPlayerNames = inputPlayerNames();
        List<Player> players = setUpPlayers(inputtedPlayerNames);
        showMessageStartingDraw(getPlayerNames(players));
        showDealerOneCard(dealer.getOneCardStatus());
        showAllPlayersCardStatus(players);

        drawAll(players);
        dealerCanDraw();
        showAllResultOfBattles(players);
        showAllResultOfDividends(players);
    }

    private void drawAll(List<Player> players) {
        players.stream()
                .filter(player -> inputDrawMoreCard(player.getNameValue()))
                .forEach(player -> player.draw(deck.draw()));
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

    private void dealerCanDraw() {
        if (dealer.isScoreLessThan16()) {
            dealer.draw(deck.draw());
            showMessageDealerWasDrawing();
        }
    }

    private void showAllResultOfBattles(final List<Player> players) {
        dealersDividends = players.stream()
                .mapToInt(player -> player.battle(dealer.getCards())).sum() * -1;
        showResultOfDealerDividends(dealersDividends);
        players.forEach(player -> showCardsResult(player.getNameValue(), player.getCards().toString(), player.getCards().getScore()));
    }

    private void showAllResultOfDividends(final List<Player> players) {
        players.forEach(player -> showResultOfDividends(player.getNameValue(), player.battle(dealer.getCards())));
         dealersDividends = players.stream()
                .mapToInt(player -> player.battle(dealer.getCards())).sum() * -1;
        showResultOfDealerDividends(dealersDividends);
    }
}