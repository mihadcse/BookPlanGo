package org.example.bookplango;

public class Traveler_Dashboard {
    Integer tour_ID,expenses;
    String destination,startDate,endDate;

    public Traveler_Dashboard(Integer tour_ID, Integer expenses, String destination, String startDate, String endDate) {
        this.tour_ID = tour_ID;
        this.expenses = expenses;
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getTour_ID() {
        return tour_ID;
    }

    public Integer getExpenses() {
        return expenses;
    }

    public String getDestination() {
        return destination;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setTour_ID(Integer tour_ID) {
        this.tour_ID = tour_ID;
    }

    public void setExpenses(Integer expenses) {
        this.expenses = expenses;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
