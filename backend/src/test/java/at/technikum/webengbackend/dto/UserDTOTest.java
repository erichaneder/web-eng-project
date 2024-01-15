package at.technikum.webengbackend.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDTOTest {
    @Test
    public void testGetterSetter() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setEmail("test@example.com");
        userDTO.setName("Test Persona");

        assertEquals(1L, userDTO.getId());
        assertEquals("test@example.com", userDTO.getEmail());
        assertEquals("Test Persona", userDTO.getName());
    }

}