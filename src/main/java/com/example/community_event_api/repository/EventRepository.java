package com.example.community_event_api.repository;

import com.example.community_event_api.entity.Event;  // Corrected import

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventRepository extends JpaRepository<Event, Long> {  // Updated to Long
    @Query("SELECT e FROM Event e")
    List<Event> findAllEvents();

}
