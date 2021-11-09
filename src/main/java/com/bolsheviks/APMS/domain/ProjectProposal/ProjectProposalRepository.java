package com.bolsheviks.APMS.domain.ProjectProposal;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjectProposalRepository extends JpaRepository<ProjectProposal, UUID> {
}
