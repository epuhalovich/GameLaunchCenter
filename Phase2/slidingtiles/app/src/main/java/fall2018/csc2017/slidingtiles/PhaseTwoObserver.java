package fall2018.csc2017.slidingtiles;

//Citation: https://www.journaldev.com/1739/observer-design-pattern-in-java
public interface PhaseTwoObserver {
    public void update();
    public void setSubject(PhaseTwoSubject subject);
}
