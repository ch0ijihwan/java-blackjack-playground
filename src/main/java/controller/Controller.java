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
    private List<ParticipantDto> playersDto;
    private ParticipantDto dealerDto;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        setUpGame();
        drawAllPlayer(players);
        drawOneCardDealer();
        showAllResultOfBattles();
        showAllResultOfDividends(players);
    }

    private void setUpGame() {
        String[] inputtedPlayerNames = inputView.inputPlayerNames();
        this.players = setUpPlayers(inputtedPlayerNames);
        upDateDto();
        outputView.showMessageStartingDraw(playersDto);
        showAllPlayersCardStatus();
    }

    private void upDateDto() {
        this.playersDto = players.stream()
                .map(ParticipantDto::from)
                .collect(Collectors.toUnmodifiableList());
        this.dealerDto = ParticipantDto.from(dealer);
    }

    private List<Player> setUpPlayers(final String[] inputtedPlayerNames) {
        return Arrays.stream(inputtedPlayerNames)
                .map(name -> new Player(name, inputView.inputBattlingMoney(name), deck.startingDraw()))
                .collect(Collectors.toUnmodifiableList());
    }

    private void showAllPlayersCardStatus() {
        outputView.showDealerOneCardStatus(dealerDto);
        outputView.showPlayerCardStatus(playersDto);
    }

    private void drawAllPlayer(final List<Player> players) {
        players.forEach(this::drawOrStay);
    }

    private void drawOrStay(final Player player) {
        while (player.canDrawCard() && inputView.inputDrawMoreCard(ParticipantDto.from(player)) == (DrawingCardAnswer.YES)) {
            player.draw(deck.provideCard());
            upDateDto();
            outputView.showPlayerCardStatus(playersDto);
        }
        if (player.canDrawCard()) {
            player.stay();
        }
    }

    private void drawOneCardDealer() {
        if (dealer.canDrawCard()) {
            dealer.draw(deck.provideCard());
            outputView.showMessageDealerWasDrawing();
        }
    }

    private void showAllResultOfBattles() {
        outputView.showCardsResult(dealerDto);
        playersDto.forEach(outputView::showCardsResult);

    }

    private void showAllResultOfDividends(final List<Player> players) {
        players.forEach(this::showResultOfOneParticipantDividends);
        int dealersDividends = players.stream()
                .mapToInt(player -> player.getDividends(dealer.getCards())).sum() * -1;
        outputView.showResultOfDividends(dealerDto, dealersDividends);
    }

    private void showResultOfOneParticipantDividends(final Player player) {
        outputView.showResultOfDividends(ParticipantDto.from(player), player.getDividends(dealer.getCards()));
    }
}
