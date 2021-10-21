package com.bolsheviks.APMS.domain.Stage;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface StageRepository extends CrudRepository<Stage, UUID> {
}
