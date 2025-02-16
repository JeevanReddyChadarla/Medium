# Medium
Medium based application, where users can post the content.

## Configuration
1. Add mongo dependency in pom
2. Configure the port, host and name in application.properties file

## work flow
RestApisController -> Service -> Repository extends MongoRepository -> JournalEntity (@Document) 

## run mongo shell
1. open mongo shell(cmd)
2. mongosh
3. show dbs
4. use journaldb
5. show collections
6. db.journalEntry.find()

## using ResponseEntity
For the invalid myId the code returns null and 200(OK) status code
```
@GetMapping("/all/id/{myId}")
    public JournalEntry getJournalById(@PathVariable ObjectId myId){
        return journalEntryService.getJournalsById(myId).orElse(null);
    }
```
To override the response status code, headers and body we use ResponseEntity
```
@GetMapping("/all/id/{myId}")
    public ResponseEntity<JournalEntry> getJournalById(@PathVariable ObjectId myId) {
        if (journalEntryService.getJournalsById(myId) != null) {
            return new ResponseEntity<>(journalEntryService.getJournalsById(myId), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
```

## Build the relation between user and journals
users has a relation with journals

```@DBRef``` to automatically store journals in users

## @Transactional - @EnableTransactionalManager - Bean creation for the implementation
The journal class should point to any one of the users.
So we have added `journal.save(newJournal)` and `user.save(newJournal)`
If `journal.save(newJournal)` is success and `user.save(newJournal)` is NOT success 
Then journals without user exists (which is incorrect).
To avoid this we use @Transaction to save.
and @EnableTransactionalManager to enable it.