package airline.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import airline.repository.KorisnikRepository;
import model.Korisnik;
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	KorisnikRepository korrep;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Korisnik k=korrep.findByKorisnickoIme(username);
		UserDetailsImpl userDetails=new UserDetailsImpl();
		userDetails.setUsername(k.getKorisnickoIme());
		userDetails.setPassword(k.getLozinka());
		userDetails.setRoles(k.getRoles());
		return userDetails;
	}

}
