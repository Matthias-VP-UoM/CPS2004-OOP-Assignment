package com.task3a_b;

/*import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;*/

public class LogisticsFacade {

    public LogisticsFacade(){}

    public void serialize() {
        // Delegate serialization to the main class
        App.save2();
    }

    public void deserialize() {
        // Delegate deserialization to the main class
        App.load2();
    }
}

