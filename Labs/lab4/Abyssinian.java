public class Abyssinian extends Cat{  
    // the inheritance goes to the first line
    // subclass extends (no from!) superclass
    public Abyssinian(String nameAbyssinian) {
        super(nameAbyssinian, true);  // how to initialize only one field of the superclass? cannot do it
    }
    public boolean isExtroverted() {
        return true;
    }
}
