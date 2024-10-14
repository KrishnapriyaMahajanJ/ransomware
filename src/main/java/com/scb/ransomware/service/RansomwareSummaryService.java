package com.scb.ransomware.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scb.ransomware.entities.RansomwareSummary;
import com.scb.ransomware.repository.RansomwareSummaryRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RansomwareSummaryService {

    final RansomwareSummaryRepository ransomwareSummaryRepository;

    final ObjectMapper objectMapper;


    public RansomwareSummaryService(RansomwareSummaryRepository ransomwareSummaryRepository, ObjectMapper objectMapper) {
        this.ransomwareSummaryRepository = ransomwareSummaryRepository;
        this.objectMapper = objectMapper;
    }

    public static List<JSONObject> convertToJsonList(InputStream inputStream) {
        JSONArray jsonArray = new JSONArray(new JSONTokener(inputStream));
        List<JSONObject> jsonObjects = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObjects.add(jsonArray.getJSONObject(i));
        }
        return jsonObjects;
    }

    public List<RansomwareSummary> addRansomwareSummary(RansomwareSummary ransomwareSummary) {
        List<RansomwareSummary> ransomwareSummary1 = getRansomwareSummary(ransomwareSummary.getName().get(0));
        if (ransomwareSummary1 == null) {
            return Collections.singletonList(ransomwareSummaryRepository.save(ransomwareSummary));
        }

        return ransomwareSummaryRepository.saveAll(ransomwareSummary1);
    }

    public List<RansomwareSummary> getRansomwareSummary(String ransomwareName) {
        return ransomwareSummaryRepository.findAllRansomwareSummariesByNameContaining(ransomwareName);
    }

    public int uploadSummary(InputStream uploadedInputStream) throws JsonProcessingException {
        List<JSONObject> jsonObjects = convertToJsonList(uploadedInputStream);
        for (JSONObject jsonObject : jsonObjects) {
            RansomwareSummary ransomwareSummary = objectMapper.readValue(jsonObject.toString(), RansomwareSummary.class);
            ransomwareSummaryRepository.save(ransomwareSummary);
        }
        return jsonObjects.size();
    }

    public String deleteRansomwareSummary(String name) {
        List<RansomwareSummary> ransomwareSummary = getRansomwareSummary(name);
        if (ransomwareSummary == null) {
            throw new IllegalArgumentException("Ransomware summary not found");
        }
        for (RansomwareSummary r : ransomwareSummary) {
            if (r.getName().size() == 1) {
                ransomwareSummaryRepository.delete(r);
            } else {
                r.getName().remove(name);
                ransomwareSummaryRepository.save(r);
            }
        }
        return "Deleted Ransomware summary";
    }
}
