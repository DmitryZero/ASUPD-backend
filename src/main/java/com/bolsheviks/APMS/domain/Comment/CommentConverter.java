package com.bolsheviks.APMS.domain.Comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentConverter {

    public CommentDto convertCommentToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.userOwnerUuid = comment.getUserOwner().getId();
        commentDto.content = comment.getContent();
        commentDto.commentId = comment.getId();
        commentDto.dateCreated = comment.getDateCreated();
        return commentDto;
    }
}
