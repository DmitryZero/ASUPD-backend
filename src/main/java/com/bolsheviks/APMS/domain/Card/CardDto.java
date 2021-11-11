package com.bolsheviks.APMS.domain.Card;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CardDto {

    public String name;
    public Date lastModifiedDate;
    public UUID lastModifiedUserId;
    public CardStatus status;
    public CardMark mark;
    public String content;
    public List<UUID> commentUuidList;
}
