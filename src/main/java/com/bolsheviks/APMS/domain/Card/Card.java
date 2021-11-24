package com.bolsheviks.APMS.domain.Card;

import com.bolsheviks.APMS.domain.BaseEntity;
import com.bolsheviks.APMS.domain.Comment.Comment;
import com.bolsheviks.APMS.domain.User.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "cards")
@Getter
@Setter
public class Card extends BaseEntity {

    @CreatedDate
    private Date dateCreated;
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
