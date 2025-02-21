package com.example.community_event_api.repository.service;

import com.example.community_event_api.entity.Guest;
import com.example.community_event_api.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuestService {

    @Autowired
    private GuestRepository guestRepository;

    // Method to save guest
    public Guest saveGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    // Method to get all guests
    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    // Method to get guest by ID
    public Optional<Guest> getGuestById(Long id) {
        return guestRepository.findById(id);
    }

    // Method to update guest
    public Guest updateGuest(Long id, Guest updatedGuest) {
        return guestRepository.findById(id)
                .map(guest -> {
                    guest.setName(updatedGuest.getName());
                    guest.setEmail(updatedGuest.getEmail());
                    guest.setPhone(updatedGuest.getPhone());
                    return guestRepository.save(guest);
                })
                .orElseThrow(() -> new RuntimeException("Guest not found"));
    }

    // Method to delete guest
    public void deleteGuest(Long id) {
        guestRepository.deleteById(id);
    }

    public Page<Guest> getAllGuests(Pageable pageable) {
        
        throw new UnsupportedOperationException("Unimplemented method 'getAllGuests'");
    }
}
