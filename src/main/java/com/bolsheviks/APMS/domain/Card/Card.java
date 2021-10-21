package com.bolsheviks.APMS.domain.Card;

import com.bolsheviks.APMS.domain.BaseEntity;
import com.bolsheviks.APMS.domain.Comment.Comment;
import com.bolsheviks.APMS.domain.User.User;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "cards")
public class Card extends BaseEntity {

    private String name;
    @LastModifiedDate
    private Date lastModifiedDate;
    @ManyToOne
    private User lastModifiedUser;
    @Enumerated(EnumType.STRING)
    private CardStatus status;
    @Enumerated(EnumType.STRING)
    private CardMark mark;
    private String content;
    @OneToMany
    private List<Comment> commentList;
}
