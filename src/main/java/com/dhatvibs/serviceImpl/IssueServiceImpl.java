package com.dhatvibs.serviceImpl;

import com.dhatvibs.dto.ReportIssueRequestDto;
import com.dhatvibs.entity.Issue;
import com.dhatvibs.entity.IssueStatus;
import com.dhatvibs.entity.Rider;
import com.dhatvibs.repository.IssueRepository;
import com.dhatvibs.repository.RiderRepository;
import com.dhatvibs.service.IssueService;
import org.springframework.stereotype.Service;

@Service
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepo;
    private final RiderRepository riderRepo;

    public IssueServiceImpl(IssueRepository issueRepo,
                            RiderRepository riderRepo) {
        this.issueRepo = issueRepo;
        this.riderRepo = riderRepo;
    }

    @Override
    public Issue reportIssue(Long riderId, ReportIssueRequestDto dto) {

        Rider rider = riderRepo.findById(riderId)
                .orElseThrow(() -> new RuntimeException("Rider not found"));

        Issue issue = new Issue();
        issue.setRider(rider);
        issue.setIssueType(dto.issueType);
        issue.setNotes(dto.notes);
        issue.setOrderId(dto.orderId);
        issue.setSlotId(dto.slotId);
        issue.setStatus(IssueStatus.OPEN);

        return issueRepo.save(issue);
    }
}
