package airline.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import airline.repository.KorisnikRepository;
import airline.repository.RoleRepository;
import model.*;

@Controller
@RequestMapping(value="auth")
public class RegistracijaController {
	
	@Autowired
	KorisnikRepository kr;
	
	@Autowired
	RoleRepository rr;
	
	@ModelAttribute
	public void getRoles(Model model) {
		List<Role> uloge=rr.findAll();
		model.addAttribute("uloge", uloge);
		
	}
	
	@RequestMapping(value="loginPage", method=RequestMethod.GET) 
	public String loginPage() { 
		return "registracija/login";
		
	}
	@RequestMapping(value="pocetna", method=RequestMethod.GET) 
	public String pocetna() { 
		return "index";
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public void loginHello() {
	   
    	
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/auth/pocetna";
    }
	
	
	@RequestMapping(value="metod1", method=RequestMethod.GET)
	public String rijesi(Model m) {
		m.addAttribute("korisnik", new Korisnik());
		
		return "registracija/Registracija1";
		
	}

		

	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    sdf.setLenient(true);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	
	@ModelAttribute("korisnik")
	public Korisnik napraviKorisnika() {
		return new Korisnik();
	}
	
	@RequestMapping(value="registruj", method=RequestMethod.POST)
	public String sacuvajKorisnika(@Valid @ModelAttribute("korisnik") Korisnik k, Errors e, Model m) {
		if(e.hasErrors()) {
			return "registracija/Registracija1";
		}
		else {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	     	k.setLozinka(passwordEncoder.encode(k.getLozinka()));
			
			List<Role> uloge=k.getRoles();
			
	     	for(Role r:uloge) {
	     		r.getKorisniks().add(k);
	     	}
			k.setRoles(uloge);
		
		   Korisnik ko=kr.save(k);
		   m.addAttribute("korisnik", ko);
		
		
	   return "registracija/login";
		}
	  
		
	}

}
