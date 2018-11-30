package fall2018.csc2017.slidingtiles;

//Citation :https://www.journaldev.com/1739/observer-design-pattern-in-java
public interface PhaseTwoSubject {

    public void register(PhaseTwoObserver obj);


    //method to notify observers of change
    public void notifyObservers();

}
