package controller;

import model.card.Deck;
import model.participant.Dealer;
import model.participant.Player;
import model.participant.dto.ParticipantDto;
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
        players.forEach(Player::stay);
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
                .map(player -> ParticipantDto.from(player).getName())
                .collect(Collectors.toUnmodifiableList());
    }

    private void showAllPlayersCardStatus(final List<Player> players) {
        ParticipantDto dealerDto = ParticipantDto.from(dealer);
        outputView.showPlayerCardStatus(dealerDto.getName(), dealerDto.getCardsStatus());
        players.forEach(player ->
                outputView.showPlayerCardStatus(ParticipantDto.from(player).getName(),
                        ParticipantDto.from(player).getCardsStatus()));
    }

    private void drawAllPlayer(final List<Player> players) {
        players.forEach(this::drawOrStay);
    }

    private void drawOrStay(final Player player) {
        ParticipantDto playerDto = ParticipantDto.from(player);
        while (player.canDrawCard() && inputView.inputDrawMoreCard(playerDto.getName()).equals(DrawingCardAnswer.YES)) {
            player.draw(deck.provideCard());
            outputView.showPlayerCardStatus(playerDto.getName(), playerDto.getCardsStatus());
        }
    }

    private void drawOneCardDealer() {
        if (dealer.canDrawCard()) {
            dealer.draw(deck.provideCard());
            outputView.showMessageDealerWasDrawing();
        }
    }

    private void showAllResultOfBattles(final List<Player> players) {
        ParticipantDto dealerDto = ParticipantDto.from(dealer);
        outputView.showCardsResult(dealerDto.getName(), dealerDto.getCardsStatus(), dealerDto.getScore());
        players.forEach(this::showResultOfOneParticipantBattle);
    }

    private void showResultOfOneParticipantBattle(final Player player) {
        ParticipantDto playerDto = ParticipantDto.from(player);
        outputView.showCardsResult(playerDto.getName(), playerDto.getCardsStatus(), playerDto.getScore());
    }

    private void showAllResultOfDividends(final List<Player> players) {
        players.forEach(this::showResultOfOneParticipantDividends);
        int dealersDividends = players.stream()
                .mapToInt(player -> player.getDividends(dealer.getCards())).sum() * -1;
        outputView.showResultOfDividends("딜러", dealersDividends);
    }

    private void showResultOfOneParticipantDividends(final Player player) {
        ParticipantDto playerDto = ParticipantDto.from(player);
        outputView.showResultOfDividends(playerDto.getName(), player.getDividends(dealer.getCards()));
    }
}