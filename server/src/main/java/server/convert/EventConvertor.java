package server.convert;

import lib.dto.AdressDto;
import lib.dto.EventDto;
import server.model.Adress;
import server.model.Event;

import java.util.Optional;

public final class EventConvertor {

    private EventConvertor() {
    }

    public static Event convert(EventDto eventDto) {
        var event = new Event();
        event.setId(eventDto.getId());
        event.setName(eventDto.getName());
        event.setDate(eventDto.getDate());
        Optional.ofNullable(eventDto.getAdress())
                .ifPresent(adressDto -> {
                    var adress = new Adress();
                    adress.setNumber(adressDto.getNumber());
                    adress.setStreet(adressDto.getStreet());
                    event.setAdress(adress);
                });
        return event;
    }

    public static EventDto convert(Event event) {
        var eventDto = new EventDto(event.getName(), event.getDate(), null, event.getUser().getId());
        Optional.ofNullable(event.getAdress())
                .ifPresent(adress -> {
                    eventDto.setAdress(new AdressDto(adress.getStreet(), adress.getNumber()));
                });
        eventDto.setId(event.getId());
        return eventDto;
    }

}
