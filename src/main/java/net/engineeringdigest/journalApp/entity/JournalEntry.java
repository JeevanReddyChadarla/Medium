package net.engineeringdigest.journalApp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JournalEntry {
    @Id
    private String id;
    private String content;
    private String title;
}
