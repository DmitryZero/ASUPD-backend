package com.bolsheviks.APMS.domain.Stage;

import com.bolsheviks.APMS.BaseEntity;
import com.bolsheviks.APMS.domain.Card.Card;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Stage extends BaseEntity {

    private String name;
    @OneToMany
    private List<Card> cardList;
}
