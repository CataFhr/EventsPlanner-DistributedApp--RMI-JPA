package lib.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class GuestDto implements Serializable, Comparable<GuestDto> {

    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String email;
    private Set<String> phoneNumbers = new HashSet<>();
    private int eventId;

    public GuestDto(String name, String email, Set<String> phoneNumbers, int eventId) {
        this.name = name;
        this.email = email;
        this.phoneNumbers = phoneNumbers;
        this.eventId = eventId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Set<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GuestDto)) return false;
        GuestDto guestDto = (GuestDto) o;
        return id == guestDto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Email: " + email + ", Phone: " + phoneNumbers;
    }

    @Override
    public int compareTo(GuestDto o) {
        return this.name.compareTo(o.name);
    }

}
