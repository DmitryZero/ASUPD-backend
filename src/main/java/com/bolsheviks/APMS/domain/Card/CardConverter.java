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
        card.setName(cardDto.name);
        card.setLastModifiedUser(modifyUser);
        card.setContent(cardDto.content);
        card.setStatus(cardDto.status);
        card.setMark(cardDto.mark);
    }
}
