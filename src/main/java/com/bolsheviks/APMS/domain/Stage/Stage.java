package com.bolsheviks.APMS.domain.Stage;

import com.bolsheviks.APMS.domain.BaseEntity;
import com.bolsheviks.APMS.domain.Card.Card;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "stages")
@Getter
@Setter
public class Stage extends BaseEntity {

    private String name;
    @OneToMany
    private List<Card> cardList;
}
