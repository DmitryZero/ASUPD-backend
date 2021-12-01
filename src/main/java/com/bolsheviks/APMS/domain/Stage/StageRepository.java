package com.bolsheviks.APMS.domain.Stage;

import com.bolsheviks.APMS.domain.Card.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface StageRepository extends JpaRepository<Stage, UUID> {

    @Query("select st from Stage st where :card member of st.cardList")
    Optional<Stage> selectByCard(Card card);
}
