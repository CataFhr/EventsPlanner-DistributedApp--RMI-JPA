package server.convert;

import lib.dto.GuestDto;
import server.model.Guest;

import java.util.HashSet;

public final class GuestConvertor {

    private GuestConvertor() {
    }

    public static Guest convert(GuestDto guestDto) {
        var guest = new Guest();
        guest.setId(guestDto.getId());
        guest.setName(guestDto.getName());
        guest.setEmail(guestDto.getEmail());
        guest.setPhoneNumbers(guestDto.getPhoneNumbers());
        return guest;
    }

    public static GuestDto convert(Guest guest) {
        var guestDto = new GuestDto(guest.getName(), guest.getEmail(), new HashSet<>(guest.getPhoneNumbers()), guest.getEvent().getId());
        guestDto.setId(guest.getId());
        return guestDto;
    }

}
