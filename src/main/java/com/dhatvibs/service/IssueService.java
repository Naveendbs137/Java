package com.dhatvibs.service;

import com.dhatvibs.dto.ReportIssueRequestDto;
import com.dhatvibs.entity.Issue;

public interface IssueService {

    Issue reportIssue(Long riderId, ReportIssueRequestDto dto);
}
