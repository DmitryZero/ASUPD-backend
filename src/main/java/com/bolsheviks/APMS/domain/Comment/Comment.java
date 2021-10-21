package com.bolsheviks.APMS.domain.Comment;

import com.bolsheviks.APMS.domain.BaseEntity;
import com.bolsheviks.APMS.domain.User.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "comments")
public class Comment extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_owner_id_id")
    private User userOwnerId;
    @CreatedDate
    private Date dateCreated;
    private String content;
}
