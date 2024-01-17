package at.technikum.webengbackend.filters;

import at.technikum.webengbackend.service.JwtService;
import at.technikum.webengbackend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.servlet.FilterChain;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class JwtAuthenticationFilterTest {

    @Mock
    private JwtService jwtService;

    @Mock
    private UserService userService;

    @Mock
    private FilterChain filterChain;

    @Mock
    private UserDetailsService userDetailsService;

    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        when(userService.userDetailsService()).thenReturn(userDetailsService);
        SecurityContextHolder.clearContext();
    }

    @Test
    public void testDoFilterInternal_ValidToken() throws Exception {
        String token = "Bearer validToken";
        request.addHeader("Authorization", token);

        when(jwtService.extractUserName(anyString())).thenReturn("user@example.com");
        when(jwtService.isTokenValid(anyString(), any(UserDetails.class))).thenReturn(true);

        UserDetails userDetails = mock(UserDetails.class);
        when(userService.userDetailsService().loadUserByUsername(anyString())).thenReturn(userDetails);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        assertNotNull(SecurityContextHolder.getContext().getAuthentication(), "Authentication should be set");
        assertTrue(SecurityContextHolder.getContext().getAuthentication() instanceof UsernamePasswordAuthenticationToken, "Authentication should be of correct type");
        verify(filterChain, times(1)).doFilter(request, response);
    }

}