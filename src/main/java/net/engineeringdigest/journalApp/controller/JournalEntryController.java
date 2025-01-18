package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntry journalEntry;

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping("/all")
    public List<JournalEntry> getAllJournals(){
        return journalEntryService.getAllJournals();
    }

    @PostMapping("/all")
    public String addJournalEntry(@RequestBody JournalEntry newJournalEntry){
        return journalEntryService.addJournalEntry(newJournalEntry);
    }

    @GetMapping("/all/id/{myId}")
    public Optional<JournalEntry> getJournalById(@PathVariable String myId){
        return journalEntryService.getJournalsById(myId);
    }

    @DeleteMapping("id/{myId}")
    public boolean deleteJournalById(@PathVariable String myId){
        return journalEntryService.deleteJournalEntryById(myId);
    }

    @PutMapping("id/{myId}")
    public String updateJournalById(@PathVariable String myId, @RequestBody JournalEntry newJournalEntry){
        return journalEntryService.updateById(myId, newJournalEntry);
    }
}
