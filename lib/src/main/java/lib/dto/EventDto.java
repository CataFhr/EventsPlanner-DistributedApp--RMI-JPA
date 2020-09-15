package lib.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class EventDto implements Serializable, Comparable<EventDto> {

    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private LocalDate date;
    private AdressDto adress;
    private int userId;
    private Set<Integer> guestsIds = new HashSet<>();
    private static final int MAXCAPACITY = 20;

    public EventDto(String name, LocalDate date, AdressDto adress, int userId) {
        this.name = name;
        this.date = date;
        this.adress = adress;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public AdressDto getAdress() {
        return adress;
    }

    public void setAdress(AdressDto adress) {
        this.adress = adress;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Set<Integer> getGuestsIds() {
        return guestsIds;
    }

    public void setGuestsIds(Set<Integer> guestsIds) {
        this.guestsIds = guestsIds;
    }

    public static int getMAXCAPACITY() {
        return EventDto.MAXCAPACITY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventDto)) return false;
        EventDto eventDto = (EventDto) o;
        return id == eventDto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Event: " + name + ", Date: " + date + ", " + adress;
    }

    @Override
    public int compareTo(EventDto o) {
        return this.getDate().compareTo(o.getDate());
    }

}
