package view.output;

import model.card.dto.CardDto;
import model.participant.dto.ParticipantDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConsoleOutput implements OutputView {
    private static final int FIRST_CARD_INDEX = 0;

    @Override
    public void showMessageStartingDraw(final List<ParticipantDto> participantDtoList) {
        List<String> names = participantDtoList.stream()
                .map(ParticipantDto::getName)
                .collect(Collectors.toUnmodifiableList());
        System.out.println("딜러와" + names + "에게 2장의 카드를 나누었습니다.");
    }

    @Override
    public void showPlayerCardStatus(final ParticipantDto participantDto) {
        System.out.println(participantDto.getName() + "의 카드 " + makeCardStatusDisplay(participantDto.getCardDtoList()));
    }

    @Override
    public void showDealerOneCardStatus(final ParticipantDto dealerDto) {
        List<CardDto> dealerOneCardStatus = List.of(dealerDto.getCardDtoList().get(FIRST_CARD_INDEX));
        System.out.println(dealerDto.getName() + "의 카드 " + makeCardStatusDisplay(dealerOneCardStatus));
    }


    private String makeCardStatusDisplay(final List<CardDto> cardDtoList) {
        List<String> cardStatus = new ArrayList<>();
        IntStream.range(0, cardDtoList.size())
                .forEach(index -> cardStatus.add(cardDtoList.get(index).getCardNumber() + "-" + cardDtoList.get(index).getCardSuit()));
        return String.join(",", cardStatus);

    }

    @Override
    public void showPlayerCardStatus(final List<ParticipantDto> participantDtoList) {
        participantDtoList.forEach(this::showPlayerCardStatus);
    }

    @Override
    public void showMessageDealerWasDrawing() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    @Override
    public void showCardsResult(final ParticipantDto participantDto) {
        System.out.println(participantDto.getName() + "카드: " +
                makeCardStatusDisplay(participantDto.getCardDtoList()) + "- 결과: " +
                participantDto.getScore());
    }

    @Override
    public void showResultOfDividends(final ParticipantDto participantDto, final int dividends) {
        System.out.println(participantDto.getName() + ":" + dividends);
    }
}
