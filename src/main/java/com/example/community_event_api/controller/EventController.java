// package com.example.community_event_api.controller;

// import com.example.community_event_api.entity.Event;
// import com.example.community_event_api.repository.service.EventService;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.data.domain.Sort;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;

// import java.util.Optional;

// @RestController
// @RequestMapping("/api/events")
// public class EventController {

//     private final EventService eventService;

//     public EventController(EventService eventService) {
//         this.eventService = eventService;
//     }

//     // @GetMapping
//     // public Page<Event> getAllEvents(@RequestParam(defaultValue = "0") int page, 
//     //                                 @RequestParam(defaultValue = "10") int size,
//     //                                 @RequestParam(defaultValue = "id") String sortBy,
//     //                                 @RequestParam(defaultValue = "asc") String sortDir) {
//     //     Pageable pageable = PageRequest.of(page, size, 
//     //                    sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
//     //     return eventService.getAllEvents(pageable);
//     // }
//     @GetMapping
//     public ResponseEntity<Page<Event>> getAllEvents(
//         @RequestParam(defaultValue = "0") int page,
//         @RequestParam(defaultValue = "10") int size,
//         @RequestParam(defaultValue = "id") String sortBy,
//         @RequestParam(defaultValue = "asc") String sortDir) {
    
//     Pageable pageable = PageRequest.of(page, size, Sort.by(sortDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));
//     Page<Event> events = eventService.getAllEvents(pageable);
    
//     return ResponseEntity.ok(events);
// }

//     @GetMapping("/{id}")
//     public Optional<Event> getEventById(@PathVariable Long id) {
//         return eventService.getEventById(id);
//     }

//     // @PostMapping
//     // public Event createEvent(@RequestBody Event event) {
//     //     return eventService.saveEvent(event);
//     // }
//     @PostMapping
//     public ResponseEntity<Event> createEvent(@RequestBody Event event) {
//         Event savedEvent = eventService.saveEvent(event);
//         return ResponseEntity.status(HttpStatus.CREATED).body(savedEvent);
//     }

//     @PutMapping("/{id}")
//     public Event updateEvent(@PathVariable Long id, @RequestBody Event event) {
//         return eventService.updateEvent(id, event);
//     }

//     @DeleteMapping("/{id}")
//     public void deleteEvent(@PathVariable Long id) {
//         eventService.deleteEvent(id);
//     }
// }
package com.example.community_event_api.controller;

import com.example.community_event_api.entity.Event;
import com.example.community_event_api.repository.EventRepository;
import com.example.community_event_api.repository.service.EventService;

// import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // @GetMapping
    // public ResponseEntity<Page<Event>> getAllEvents(
    //     @RequestParam(defaultValue = "0") int page,
    //     @RequestParam(defaultValue = "10") int size,
    //     @RequestParam(defaultValue = "id") String sortBy,
    //     @RequestParam(defaultValue = "asc") String sortDir) {

    //     Pageable pageable = PageRequest.of(page, size, Sort.by(sortDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));
    //     Page<Event> events = eventService.getAllEvents(pageable);
        
    //     return ResponseEntity.ok(events);
    // }
    // @GetMapping("/events")
    // public List<Event> getAllEvents() {
    // return eventService.getAllEvents();
    // }
    @GetMapping("/events")
    public ResponseEntity<Page<Event>> getAllEvents(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "asc") String sortDir) {

    Page<Event> events = eventService.getAllEvents(page, size, sortBy, sortDir);
    return ResponseEntity.ok(events);
}

     @Autowired
    private EventRepository eventRepository;
    @GetMapping("/events")
    public ResponseEntity<?> getAllEvents(
        @RequestParam(required = false) Integer page,
        @RequestParam(required = false) Integer size) {

    if (page != null && size != null) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<Event> eventPage = eventRepository.findAll(pageable);
        return ResponseEntity.ok(eventPage.getContent());
    } else {
        return ResponseEntity.ok(eventRepository.findAll());
    }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        return eventService.getEventById(id)
            .map(event -> ResponseEntity.ok(event))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event savedEvent = eventService.saveEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event event) {
        Event updatedEvent = eventService.updateEvent(id, event);
        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
