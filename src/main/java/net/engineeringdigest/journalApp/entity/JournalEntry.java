package net.engineeringdigest.journalApp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Document
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JournalEntry {
    @Id
    private ObjectId id;
    private String content;
    private String title;
    private LocalDateTime localDateTime;
}
