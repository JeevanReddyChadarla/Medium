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
3. showdbs
4. use journaldb
5. show collections
6. db.journalEntry.find()
