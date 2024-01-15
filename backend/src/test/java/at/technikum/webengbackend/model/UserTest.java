package at.technikum.webengbackend.model;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    public void testGetterSetter() {
        User user = new User();

        user.setId(1L);
        user.setSalutation("Mr");
        user.setEmail("test@example.com");
        user.setName("John Doe");
        user.setPassword("securePassword");
        user.setAddress(new Address("Street", "City", "12345","aut"));
        user.setRole(Role.ROLE_ADMIN);
        user.setCreatedOn(Instant.now());
        user.setLastUpdatedOn(Instant.now());
        user.setPhonenumber("123456789");
        user.setProfilePicture("base64encodedString");

        assertEquals(1L, user.getId());
        assertEquals("Mr", user.getSalutation());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("John Doe", user.getName());
        assertEquals("securePassword", user.getPassword());
        assertEquals(new Address("Street", "City", "12345","aut"), user.getAddress());
        assertEquals(Role.ROLE_ADMIN, user.getRole());
        assertNotNull(user.getCreatedOn());
        assertNotNull(user.getLastUpdatedOn());
        assertEquals("123456789", user.getPhonenumber());
        assertEquals("base64encodedString", user.getProfilePicture());
    }

    @Test
    public void testAuthorities() {
        User user = new User();
        user.setRole(Role.ROLE_ADMIN);

        assertEquals(1, user.getAuthorities().size());
        assertTrue(user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }

}