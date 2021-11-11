package com.bolsheviks.APMS.domain.Card;

import com.bolsheviks.APMS.domain.BaseEntity;
import com.bolsheviks.APMS.domain.User.User;
import org.springframework.stereotype.Component;

@Component
public class CardConverter {

    public CardDto convertCardToDto(Card card) {
        CardDto cardDto = new CardDto();
        cardDto.name = card.getName();
        cardDto.lastModifiedDate = card.getLastModifiedDate();
        cardDto.lastModifiedUserId = card.getLastModifiedUser().getId();
        cardDto.content = card.getContent();
        cardDto.status =card.getStatus();
        cardDto.mark = card.getMark();
        cardDto.commentUuidList = card.getCommentList()
                .stream().map(BaseEntity::getId).toList();
        return cardDto;
    }

    public void fillCardByDto(Card card, CardDto cardDto, User modifyUser) {
        if (cardDto.name != null) {
            card.setName(cardDto.name);
        }
        card.setLastModifiedUser(modifyUser);
        if (cardDto.content != null) {
            card.setContent(cardDto.content);
        }
        if (cardDto.status != null) {
            card.setStatus(cardDto.status);
        }
        if (cardDto.mark != null) {
            card.setMark(cardDto.mark);
        }
    }
}
