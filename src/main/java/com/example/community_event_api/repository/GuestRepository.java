package com.example.community_event_api.repository;

import com.example.community_event_api.entity.Guest;  // Corrected import
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {  // Updated to Long
}
