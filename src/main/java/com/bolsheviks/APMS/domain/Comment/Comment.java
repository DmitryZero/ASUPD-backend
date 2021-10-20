package com.bolsheviks.APMS.domain.Comment;

import com.bolsheviks.APMS.BaseEntity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import java.util.Date;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Comment extends BaseEntity {

    private UUID userOwnerId;
    @CreatedDate
    private Date dateCreated;
    private String content;

    public void setUserOwnerId(UUID userOwnerId) {
        this.userOwnerId = userOwnerId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UUID getUserOwnerId() {
        return userOwnerId;
    }

    public String getContent() {
        return content;
    }
}
