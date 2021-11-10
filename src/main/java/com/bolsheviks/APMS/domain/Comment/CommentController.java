package com.bolsheviks.APMS.domain.Comment;

import com.bolsheviks.APMS.domain.Card.Card;
import com.bolsheviks.APMS.domain.Card.CardRepository;
import com.bolsheviks.APMS.domain.Project.Project;
import com.bolsheviks.APMS.domain.Project.ProjectRepository;
import com.bolsheviks.APMS.domain.User.User;
import com.bolsheviks.APMS.domain.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

import static com.bolsheviks.APMS.security.SecurityFilter.USER_UUID;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private ProjectRepository projectRepository;
    private UserRepository userRepository;
    private CardRepository cardRepository;
    private CommentRepository commentRepository;
    private CommentConverter commentConverter;

    @PostMapping
    public void createComment(@RequestAttribute(USER_UUID) UUID userId,
                              @RequestParam("projectUuid") UUID projectId,
                              @RequestParam("cardUuid") UUID cardId,
                              @RequestBody CommentDto commentDto) {
        User user
                = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!isUserInProject(user, projectId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Card card = getCard(cardId);

        Comment comment = new Comment(user, commentDto.content);
        commentRepository.save(comment);

        card.getCommentList().add(comment);
        cardRepository.save(card);
    }

    @DeleteMapping("/{uuid}")
    public void deleteComment(@RequestAttribute(USER_UUID) UUID userId,
                              @PathVariable("uuid") UUID commentId,
                              @RequestParam("projectUuid") UUID projectId,
                              @RequestParam("cardUuid") UUID cardId) {
        if (!isUserInProject(userId, projectId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Card card = getCard(cardId);
        Comment comment
                = commentRepository.findById(commentId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        card.getCommentList().remove(comment);
        cardRepository.save(card);

        commentRepository.deleteById(commentId);
    }

    @GetMapping("/get_all")
    public List<CommentDto> getAllComments(@RequestAttribute(USER_UUID) UUID userId,
                                           @RequestParam("projectUuid") UUID projectId,
                                           @RequestParam("cardUuid") UUID cardId) {
        if (!isUserInProject(userId, projectId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Card card = getCard(cardId);
        return commentRepository
                .findAllById(card.getCommentList().stream().map(Comment::getId).toList())
                .stream()
                .map(commentConverter::convertCommentToDto)
                .toList();
    }


    private boolean isUserInProject(UUID userId, UUID projectId) {
        User user
                = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return isUserInProject(user, projectId);
    }

    private boolean isUserInProject(User user, UUID projectId) {
        Project project
                = projectRepository.findById(projectId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return project.containsUser(user);
    }

    private Card getCard(UUID cardId) {
        return cardRepository.findById(cardId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}

