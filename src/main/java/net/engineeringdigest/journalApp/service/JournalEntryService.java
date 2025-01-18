package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public List<JournalEntry> getAllJournals(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getJournalsById(String id) {
        if(journalEntryRepository.findById(id).isPresent()){
            return journalEntryRepository.findById(id);
        }
        return Optional.empty();
    }

    public String addJournalEntry(JournalEntry newJournalEntry) {
        journalEntryRepository.save(newJournalEntry);
        return "Successfully added";
    }

    public boolean deleteJournalEntryById(String id){
        journalEntryRepository.deleteById(id);
        return !getJournalsById(id).isPresent();
    }

    public String updateById(String id, JournalEntry newJournalEntry){
        Optional<JournalEntry> oldJournalEntry = getJournalsById(id);
        if(oldJournalEntry.isPresent()){
            JournalEntry journalEntry = new JournalEntry();
            journalEntry.setId(id);
            journalEntry.setTitle(newJournalEntry.getTitle());
            journalEntry.setContent(newJournalEntry.getContent());
            journalEntryRepository.save(journalEntry);
            return "Successfully updated with id: "+id;
        }
        return "No record found for the id: "+id;

    }
}
