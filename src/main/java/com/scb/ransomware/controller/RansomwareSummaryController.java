package com.scb.ransomware.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.scb.ransomware.entities.RansomwareSummary;
import com.scb.ransomware.service.RansomwareSummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequiredArgsConstructor
public class RansomwareSummaryController {


    final RansomwareSummaryService ransomwareSummaryService;

    @PostMapping("/v1/ransomware/summary")
    public ResponseEntity createRansomwareSummary(@RequestBody RansomwareSummary ransomwareSummary) {
        return ResponseEntity.ok().body(ransomwareSummaryService.addRansomwareSummary(ransomwareSummary));
    }

    @GetMapping("/v1/ransomware/summary")
    public ResponseEntity getRansomwareSummary(@Param("name") String name) {
        return ResponseEntity.ok().body(ransomwareSummaryService.getRansomwareSummary(name));
    }

    @DeleteMapping("/v1/ransomware/summary")
    public ResponseEntity deleteRansomwareSummary(@Param("name") String name) {
        return ResponseEntity.ok().body(ransomwareSummaryService.deleteRansomwareSummary(name));
    }

    @PostMapping(value = "/v1/upload/ransomware/summary", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity uploadSummary(@RequestParam("file") MultipartFile file) {
        try {
            // Process the file
            int summary = ransomwareSummaryService.uploadSummary(file.getInputStream());
            return ResponseEntity.ok(summary);

        } catch (JsonProcessingException e) {
            // If JSON processing error occurs
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (IOException e) {
            // Handle IOException (file reading issues)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}