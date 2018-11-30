package fall2018.csc2017.gamecentre;

//Citation: https://www.journaldev.com/1739/observer-design-pattern-in-java
public interface PhaseTwoObserver {
    void update();
    void setSubject(PhaseTwoSubject subject);
}
