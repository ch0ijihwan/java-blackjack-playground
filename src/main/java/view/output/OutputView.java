package view.output;

import model.participant.dto.ParticipantDto;

import java.util.List;

public interface OutputView {
    void showMessageStartingDraw(final List<ParticipantDto> participantDtoList);

    void showPlayerCardStatus(final ParticipantDto participantDto);

    void showPlayerCardStatus(final List<ParticipantDto> participantDto);

    void showMessageDealerWasDrawing();

    void showCardsResult(final ParticipantDto participantDto);

    void showResultOfDividends(final ParticipantDto participantDto, int dividends);
}
