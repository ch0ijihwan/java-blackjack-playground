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

        List<Player> players = Arrays.stream(inputtedPlayerNames)
                .map(name -> new Player(name, inputBattlingMoney(name), deck.startingDraw()))
                .collect(Collectors.toUnmodifiableList());

        showMessageStartingDraw(players.stream()
                .map(Player::getNameValue)
                .collect(Collectors.toUnmodifiableList()));

        showDealerOneCard(dealer.getOneCardStatus());

        players.forEach(player -> showPlayerCardStatus(player.getNameValue(), player.getCards().toString()));

        players.stream()
                .filter(player -> inputDrawMoreCard(player.getNameValue()))
                .forEach(player -> player.draw(deck.draw()));

        dealerCanDraw();
        showResultOfDealerCards(dealer.getCards().toString(), dealer.getScore());
        players.forEach(player -> showCardsResult(player.getNameValue(), player.getCards().toString(), player.getCards().getScore()));

        players.forEach(player -> showResultOfDividends(player.getNameValue(), player.battle(dealer.getCards())));

        int dealersDividends = players.stream().mapToInt(player -> player.battle(dealer.getCards())).sum() * -1;
        showResultOfDealerDividends(dealersDividends);

    }

    private void dealerCanDraw() {
        if (dealer.isScoreLessThan16()) {
            dealer.draw(deck.draw());
            showMessageDealerWasDrawing();
        }
    }
}
