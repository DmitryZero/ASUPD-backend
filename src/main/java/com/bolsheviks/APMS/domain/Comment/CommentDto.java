package com.bolsheviks.APMS.domain.Comment;

import java.util.Date;
import java.util.UUID;

public class CommentDto {

    public UUID commentId;
    public Date dateCreated;
    public UUID userOwnerUuid;
    public String content;
}
