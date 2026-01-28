/*
 * package com.dhatvibs.controller;
 * 
 * import com.dhatvibs.dto.ReportIssueRequestDto; import
 * com.dhatvibs.entity.Issue; import com.dhatvibs.service.IssueService; import
 * org.springframework.web.bind.annotation.*;
 * 
 * import java.util.Map;
 * 
 * @RestController
 * 
 * @RequestMapping("/api/issues") public class IssueController {
 * 
 * private final IssueService service;
 * 
 * public IssueController(IssueService service) { this.service = service; }
 * 
 * @PostMapping("/report") public Map<String, Object> reportIssue(
 * 
 * @RequestHeader("X-RIDER-ID") Long riderId,
 * 
 * @RequestBody ReportIssueRequestDto dto) {
 * 
 * Issue issue = service.reportIssue(riderId, dto);
 * 
 * return Map.of( "success", true, "message", "Issue reported successfully",
 * "data", Map.of( "_id", issue.getId(), "riderId", riderId, "issueType",
 * issue.getIssueType(), "notes", issue.getNotes(), "status", issue.getStatus(),
 * "createdAt", issue.getCreatedAt() ) ); } }
 */ 

package com.dhatvibs.controller;

import com.dhatvibs.dto.ReportIssueRequestDto;
import com.dhatvibs.entity.Issue;
import com.dhatvibs.service.IssueService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    private final IssueService service;

    public IssueController(IssueService service) {
        this.service = service;
    }

    @PostMapping("/report")
    public Map<String, Object> reportIssue(
            @RequestBody ReportIssueRequestDto dto) {

        Long riderId = getRiderIdFromToken();

        Issue issue = service.reportIssue(riderId, dto);

        return Map.of(
                "success", true,
                "message", "Issue reported successfully",
                "data", Map.of(
                        "_id", issue.getId(),
                        "riderId", riderId,
                        "issueType", issue.getIssueType(),
                        "notes", issue.getNotes(),
                        "status", issue.getStatus(),
                        "createdAt", issue.getCreatedAt()
                )
        );
    }

    // 🔐 Helper method
    private Long getRiderIdFromToken() {
        return (Long) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
