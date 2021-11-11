package com.bolsheviks.APMS.domain.Card;

import com.bolsheviks.APMS.domain.Stage.Stage;
import com.bolsheviks.APMS.domain.Stage.StageRepository;
import com.bolsheviks.APMS.domain.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardConverter cardConverter;
    private final CardRepository cardRepository;
    private final StageRepository stageRepository;

    @Transactional
    public Card createCard(User userCreator, CardDto cardDto, Stage stage) {
        Card card = new Card();
        cardConverter.fillCardByDto(card, cardDto, userCreator);
        cardRepository.save(card);
        stage.getCardList().add(card);
        stageRepository.save(stage);
        return card;
    }
}
