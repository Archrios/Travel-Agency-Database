package model;

public class ScheduledEvents {
    private int planID;
    private int scheduleID;
    private int eventID;

    public ScheduledEvents(int planID, int scheduleID, int eventID) {
        this.planID = planID;
        this.scheduleID = scheduleID;
        this.eventID = eventID;
    }

    public int getPlanID() {
        return planID;
    }

    public int getScheduleID() {
        return scheduleID;
    }

    public int getEventID() {
        return eventID;
    }
}
