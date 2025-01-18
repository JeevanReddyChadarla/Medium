package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntry journalEntry;

    private Map<Long, JournalEntry> map = new HashMap<>();

    @GetMapping("/all")
    public List<JournalEntry> getAllJournals(){
        return new ArrayList<>(map.values());
    }

    @PostMapping
    public String addJournalEntry(@RequestBody JournalEntry newJournalEntry){
        map.put(newJournalEntry.getId(), newJournalEntry);
        return "Successfully added";
    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalById(@PathVariable Long myId){
        return map.getOrDefault(myId, null);
    }

    @DeleteMapping("id/{myId}")
    public boolean deleteJournalById(@PathVariable Long myId){
        map.remove(myId);
        return map.get(myId)==null;
    }

    @PutMapping("id/{myId}")
    public String updateJournalById(@PathVariable Long myId, @RequestBody JournalEntry newJournalEntry){
        map.put(myId, newJournalEntry);
        return "Success";
    }
}
