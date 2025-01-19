package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    public List<JournalEntry> getAllJournals() {
        return journalEntryRepository.findAll();
    }

    public JournalEntry getJournalsById(ObjectId id) {
        return journalEntryRepository.findById(id).orElse(null);
    }

    public boolean addJournalEntry(JournalEntry newJournalEntry, String userName) {
        User user = userService.getUserByUserName(userName);
        newJournalEntry.setLocalDateTime(LocalDateTime.now());
        journalEntryRepository.save(newJournalEntry);
        user.getJournalEntries().add(newJournalEntry);
        userService.addNewUser(user);
        return true;
    }

    public boolean deleteJournalEntryById(String userName, ObjectId id) {
        User user = userService.getUserByUserName(userName);
        JournalEntry journalById = journalEntryRepository.findById(id).orElse(null);
        if(journalById!=null && user!=null){
            user.getJournalEntries().remove(journalById);
            journalEntryRepository.deleteById(id);
            userService.addNewUser(user);
            return true;
        }else{
            return false;
        }
    }

    public Boolean updateById(String userName, ObjectId id, JournalEntry newJournalEntry) {
        User user = userService.getUserByUserName(userName);
        if(user!=null) {
            JournalEntry oldJournalEntry = getJournalsById(id);
            if (oldJournalEntry != null) {
                JournalEntry journalEntry = new JournalEntry();
                journalEntry.setId(id);
                journalEntry.setTitle(newJournalEntry.getTitle());
                journalEntry.setContent(newJournalEntry.getContent());
                journalEntryRepository.save(journalEntry);
                user.getJournalEntries().remove(oldJournalEntry);
                user.getJournalEntries().add(journalEntry);
                userService.addNewUser(user);
                return true;
            }
        }
        return false;
    }
}
