package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;
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
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("/{userName}")
    public ResponseEntity<List<JournalEntry>> getAllJournalsByUserName(@PathVariable String userName) {
        User user = userService.getUserByUserName(userName);
        List<JournalEntry> journalEntries = user.getJournalEntries();
        return new ResponseEntity<>(journalEntries, HttpStatus.OK);
    }

    @PostMapping("/{userName}")
    public ResponseEntity<Boolean> addJournalEntry(@RequestBody JournalEntry newJournalEntry, @PathVariable String userName) {
        if(userService.getUserByUserName(userName)!=null){
            journalEntryService.addJournalEntry(newJournalEntry, userName);
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getJournalById(@PathVariable ObjectId myId) {
        JournalEntry journalsById = journalEntryService.getJournalsById(myId);
        if (journalsById != null) {
            return new ResponseEntity<>(journalsById, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{userName}/{myId}")
    public ResponseEntity<?> deleteJournalById(@PathVariable String userName, @PathVariable ObjectId myId) {
        if (journalEntryService.getJournalsById(myId) != null) {
            journalEntryService.deleteJournalEntryById(userName, myId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("id/{userName}/{myId}")
    public ResponseEntity<Boolean> updateJournalById(@PathVariable String userName, @PathVariable ObjectId myId, @RequestBody JournalEntry newJournalEntry) {
        if(journalEntryService.updateById(userName, myId, newJournalEntry)){
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }
}
