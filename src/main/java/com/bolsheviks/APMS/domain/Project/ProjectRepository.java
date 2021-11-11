package com.bolsheviks.APMS.domain.Project;

import com.bolsheviks.APMS.domain.Card.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {

    @Query("select p from Project p join p.stageList s where :card member of s.cardList")
    Optional<Project> selectByCard(Card card);
}
