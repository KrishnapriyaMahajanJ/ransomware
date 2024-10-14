package com.scb.ransomware.entities;

//import jakarta.persistence.ElementCollection;
//import jakarta.persistence.Id;
//import jakarta.persistence.ElementCollection;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(collection = "ransomware_summary")
@Getter
@Setter
public class RansomwareSummary {

    List<String> name;
    List<String> resources;
    String extensions;
    String extensionPattern;
    String ransomNoteFilenames;
    String comment;
    String encryptionAlgorithm;
    String decryptor;
    String screenshots;
    String microsoftDetectionName;
    String microsoftInfo;
    String sandbox;
    String iocs;
    String snort;
}




