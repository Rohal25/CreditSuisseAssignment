ServerLog Description : 
 This custom-build server logs takes input text file with .json as extension  . Every event has 2 entries in the file - one
entry when the event was started and another when the event was finished. The entries in the file have no specific
order (a finish event could occur before a start event for a given id)  this Program store the computed value  in hsql db for every event with their duration and a flag set to true indicating the duration is greater than 4 ms.

How to run 
1)Place json file at this location : src\jsonFiles
2)Run the Main class file .

What next steps can be added .
1)Unit testing 
2)Multithreading can be added during insertion of entries from HashMap to HSQL Db .