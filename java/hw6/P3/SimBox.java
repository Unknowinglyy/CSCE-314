
/* Written by Hyunyoung Lee for CSCE 314 Students Homework 6 Problem 3
   First, study how this class should work with the test code in SimMain.java
   carefully!

   Student Name: Blake Dejohn
   Student UIN: 531002472
   Acknowledgements: 
   Oracle Java Documentation on Synchonized Methods: https://docs.oracle.com/javase/tutorial/essential/concurrency/syncmeth.html#:~:text=Synchronized%20methods%20enable%20a%20simple,are%20done%20through%20synchronized%20methods.

   Used this type for messages and myMessages rather than a linked list because of the thread safe implementation it offers (hope this is ok since there was not restrictions on using different types for our class members): https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ConcurrentLinkedQueue.html

   Synchonization on methods and variables: https://www.baeldung.com/java-synchronize-static-variable-different-threads
*/

import java.util.*;
import java.util.concurrent.*;
class SimBox implements Runnable {
  static final int MAX_SIZE = 10;

  class Message {
    String sender;
    String recipient;
    String msg;
    Message(String sender, String recipient, String msg) {
      this.sender = sender;
      this.recipient = recipient;
      this.msg = msg;
    }
  }

  private final ConcurrentLinkedQueue<Message> messages;
  private ConcurrentLinkedQueue<Message> myMessages;
  private String myId;
  private boolean stop = false;

  public SimBox(String myId) {
    //create the shared message queue (messages)
    messages = new ConcurrentLinkedQueue<Message>();
    //set the person's name
    this.myId = myId;
    //create the private message queue (myMessages)
    this.myMessages = new ConcurrentLinkedQueue<Message>();
    //start the thread!
    new Thread(this).start();
  }

  public SimBox(String myId, SimBox s) {
    //take the messages quque from the other SimBox
    this.messages = s.messages;
    //set the person's name
    this.myId = myId;
    //create the private message queue (myMessages)
    this.myMessages = new ConcurrentLinkedQueue<Message>();
    //start the thread!
    new Thread(this).start();
  }
  //simple getter for the person's name 
  public String getId() { return myId; }

  public void stop() {
    // make it so that this Runnable will stop
    this.stop = true;
  }

  public synchronized void send(String recipient, String msg) {
    //locks because of the synchronized keyword
    //add a message to the shared message queue (messages)
    this.messages.add(new Message(this.myId, recipient, msg));
    //notify sleeping threads when done
    notifyAll();
    //unlocks when done
  }

  public synchronized List<String> retrieve() {
    //locks because of the synchronized keyword
    // return the contents of myMessages
    List<String> results = new ArrayList<String>();
    // each message should be in the following format:
    //   From (the sender) to (the recipient): (actual message)
    for(Message m : myMessages){
      results.add("From " + m.sender + " to " + m.recipient + ": " + m.msg);
    }
    // and empty myMessages
    myMessages.clear();
    //unlocks when done
    return results;
  }

  public void run() {
  // loop forever
  // 1. Approximately once every second move all messages
  //    addressed to this mailbox from the shared message queue
  //    to the private myMessages queue
  //    To do so, you need to synchronize messages and myMessages.
  //    Furthermore, you need to explicitly use the iterator() of messages
  //    with a while loop.  A for-each loop will not work here.
  // 2. Also approximately once every second, if the message
  //    queue has more than MAX_SIZE messages, delete oldest messages
  //    so that size is at most MAX_SIZE. This part of code is provided
  //    below.

    for(;;) { // loop forever
      // synchronize messages and myMessages
      synchronized(messages){
        synchronized(myMessages){
          // have the iterator of messages referred by iter of type Iterator<Message>
          Iterator<Message> iter = messages.iterator();
          // while there is more to access on iter, access the message
          while(iter.hasNext()){
            Message m = iter.next();
            // if the message's recipient is equal to myId,
            if(m.recipient.equals(myId)){
              //then remove the message from messages and add the message to myMessages
              myMessages.add(m);
              iter.remove();
            }
          }
        }
        // end of synchronized myMessages
      }
      // end of synchronized messages
      while (messages.size() > MAX_SIZE) { messages.remove(); }
      //stops the thread if true, which breaks out of the loop
      if (stop) return;
      //takes care of the 'periodically wakes up' part of the problem, sleeps for 1 second and then continues
      try { Thread.sleep(1000); } catch (InterruptedException e) {}
    } 
    // endfor
  } 
  // end run()
} 
// end SimBox


