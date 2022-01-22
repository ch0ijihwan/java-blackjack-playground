package view.input;

import model.participant.dto.ParticipantDto;

public interface InputView {
    String[] inputPlayerNames();

    int inputBattlingMoney(final String name );

    DrawingCardAnswer inputDrawMoreCard(final ParticipantDto participantDto);
}
