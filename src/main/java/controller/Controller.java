package controller;

import model.card.Deck;
import model.participant.Dealer;
import model.participant.Player;
import model.participant.state.State;
import view.input.DrawingCardAnswer;
import view.input.InputView;
import view.output.OutputView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {
    private final Deck deck = new Deck();
    private final Dealer dealer = new Dealer(deck.startingDraw());
    private final InputView inputView;
    private final OutputView outputView;
    private State state;
    private List<Player> players;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        setUpGame();
        drawAllPlayer(players);
        drawOneCardDealer();
        showAllResultOfBattles(players);
        showAllResultOfDividends(players);
    }

    private void setUpGame() {
        String[] inputtedPlayerNames = inputView.inputPlayerNames();
        this.players = setUpPlayers(inputtedPlayerNames);
        outputView.showMessageStartingDraw(getPlayerNames(players));
        showAllPlayersCardStatus(players);
    }

    private List<Player> setUpPlayers(final String[] inputtedPlayerNames) {
        return Arrays.stream(inputtedPlayerNames)
                .map(name -> new Player(name, inputView.inputBattlingMoney(name), deck.startingDraw()))
                .collect(Collectors.toUnmodifiableList());
    }

    private List<String> getPlayerNames(final List<Player> players) {
        return players.stream()
                .map(Player::getNameValue)
                .collect(Collectors.toUnmodifiableList());
    }

    private void showAllPlayersCardStatus(final List<Player> players) {
        outputView.showPlayerCardStatus(dealer.getNameValue(), dealer.getOneCardStatus());
        players.forEach(player -> outputView.showPlayerCardStatus(player.getNameValue(), player.getCards().toString()));
    }

    private void drawAllPlayer(final List<Player> players) {
        players.forEach(this::drawOrStay);
    }

    private void drawOrStay(final Player player) {
        while (player.canDrawCard() && inputView.inputDrawMoreCard(player.getNameValue()).equals(DrawingCardAnswer.YES)) {
            player.draw(deck.provideCard());
            outputView.showPlayerCardStatus(player.getNameValue(), player.getCards().toString());
        }
    }

    private void drawOneCardDealer() {
        if (dealer.canDrawCard()) {
            dealer.draw(deck.provideCard());
            outputView.showMessageDealerWasDrawing();
        }
    }

    private void showAllResultOfBattles(final List<Player> players) {
        outputView.showCardsResult("딜러", dealer.getCards().toString(), dealer.getScore());
        players.forEach(player -> outputView.showCardsResult(player.getNameValue(), player.getCards().toString(), player.getCards().getScore()));
    }

    private void showAllResultOfDividends(final List<Player> players) {
        players.forEach(Player::stay);
        players.forEach(player -> outputView.showResultOfDividends(player.getNameValue(), player.getDividends(dealer.getCards())));
        int dealersDividends = players.stream()
                .mapToInt(player -> player.getDividends(dealer.getCards())).sum() * -1;
        outputView.showResultOfDividends("딜러", dealersDividends);
    }
}