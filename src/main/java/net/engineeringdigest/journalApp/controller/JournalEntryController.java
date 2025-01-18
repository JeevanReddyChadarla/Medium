package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntry journalEntry;

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping("/all")
    public ResponseEntity<List<JournalEntry>> getAllJournals() {
        return new ResponseEntity<List<JournalEntry>>(journalEntryService.getAllJournals(), HttpStatus.OK);
    }

    @PostMapping("/all")
    public ResponseEntity<Boolean> addJournalEntry(@RequestBody JournalEntry newJournalEntry) {
        if (journalEntryService.addJournalEntry(newJournalEntry)) {
            return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
        }
        return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/all/id/{myId}")
    public ResponseEntity<JournalEntry> getJournalById(@PathVariable ObjectId myId) {
        if (journalEntryService.getJournalsById(myId) != null) {
            return new ResponseEntity<>(journalEntryService.getJournalsById(myId), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteJournalById(@PathVariable ObjectId myId) {
        if (journalEntryService.getJournalsById(myId) != null) {
            journalEntryService.deleteJournalEntryById(myId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<Boolean> updateJournalById(@PathVariable ObjectId myId, @RequestBody JournalEntry newJournalEntry) {
        if(journalEntryService.updateById(myId, newJournalEntry)){
            return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
        }
        return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
    }
}
