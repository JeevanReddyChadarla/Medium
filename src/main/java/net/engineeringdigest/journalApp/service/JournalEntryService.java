package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public List<JournalEntry> getAllJournals() {
        return journalEntryRepository.findAll();
    }

    public JournalEntry getJournalsById(ObjectId id) {
        return journalEntryRepository.findById(id).orElse(null);
    }

    public boolean addJournalEntry(JournalEntry newJournalEntry) {
        newJournalEntry.setLocalDateTime(LocalDateTime.now());
        journalEntryRepository.save(newJournalEntry);
        return true;
    }

    public void deleteJournalEntryById(ObjectId id) {
        journalEntryRepository.deleteById(id);
        getJournalsById(id);
    }

    public Boolean updateById(ObjectId id, JournalEntry newJournalEntry) {
        JournalEntry oldJournalEntry = getJournalsById(id);
        if (oldJournalEntry != null) {
            JournalEntry journalEntry = new JournalEntry();
            journalEntry.setId(id);
            journalEntry.setTitle(newJournalEntry.getTitle());
            journalEntry.setContent(newJournalEntry.getContent());
            journalEntryRepository.save(journalEntry);
            return true;
        }
        return false;
    }
}
