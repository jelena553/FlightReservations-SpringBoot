package airline.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import airline.repository.KorisnikRepository;
import airline.repository.LetRepository;
import airline.repository.LinijaRepository;
import airline.repository.RezervacijaRepository;
import airline.repository.SjedisteRepository;
import model.Korisnik;
import model.Let;
import model.Linija;
import model.Rezervacija;
import model.Sjediste;

@Controller
@RequestMapping(value="rezervacija")
public class RezervacijaController {
	
	@Autowired 
	LetRepository lr;
	
	@Autowired
	LinijaRepository linRep;
	
	@Autowired
	KorisnikRepository kr;
	
	@Autowired
	SjedisteRepository sr;
	
	@Autowired
	RezervacijaRepository rr;
	
	@RequestMapping(value="pretraga", method=RequestMethod.GET)
	public String pretraga() {
		return "pretraga/search";
		
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    sdf.setLenient(true);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	
	@RequestMapping(value="pronadjiLetove", method=RequestMethod.GET)
	public String pronadjiLetoveZaDatum(String from, String to, Date datumPolaska, HttpServletRequest request, Model m) {
		
		if(datumPolaska.before(new Date())) {
			m.addAttribute("greskaDatumKorisnik", true);
			
			return "pretraga/rezultatiPretrage";
		}
		List<Let> letovi=lr.vratiLetove(from, to, datumPolaska);
		request.getSession().setAttribute("datum", datumPolaska);
		request.getSession().setAttribute("from", from);
		request.getSession().setAttribute("to", to);
		request.getSession().setAttribute("letovi", letovi);
		
		return "pretraga/rezultatiPretrage";
	}
	
	@RequestMapping(value="detaljiRezervacije", method=RequestMethod.GET)
	public String detaljiRezervacije(Integer idLet, String klase, HttpServletRequest request, Model m) {
		Let let=lr.findById(idLet).get();
		
		Linija linija=let.getLinija();
		request.getSession().setAttribute("let", let);
		
		if(klase.equals("ec")) {
			request.getSession().setAttribute("klasa", "ECONOMY");
			request.getSession().setAttribute("cijena",linija.getCijenaEconomy());
		}
		else {
			request.getSession().setAttribute("klasa", "BUSINESS");
			request.getSession().setAttribute("cijena",linija.getCijenaBusiness() );
		}
		
		return "rezervacija/potvrdaRezervacije";
		
	}
	
	@RequestMapping(value="potvrdaRezervacije", method=RequestMethod.GET)
	public String potvrda( HttpServletRequest request) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		Rezervacija r=new Rezervacija();
		Korisnik k=kr.findByKorisnickoIme(currentPrincipalName);
		Integer cijena=(Integer)request.getSession().getAttribute("cijena");
		
		Let l=(Let)request.getSession().getAttribute("let");
		String klasa=(String)request.getSession().getAttribute("klasa");
		List<Sjediste> sjedista;
		
		
		r.setKorisnik(k);
		r.setLet(l);
		r.setDatumPlacanja(new Date());
		r.setCijena(cijena);
		
		
		if(klasa.equals("ECONOMY")) {
			 sjedista=sr.slobodnaSjedista(l.getIdLet(), l.getAvion().getBrRedovaUkupno(), l.getAvion().getBrRedovaBusiness());
			r.setSjediste(sjedista.get(0));
		}
		else {
		
          sjedista=sr.slobodnaSjedista(l.getIdLet(),l.getAvion().getBrRedovaBusiness() );
		  r.setSjediste(sjedista.get(0));
		}
		Rezervacija reze=rr.save(r);
		request.getSession().setAttribute("uspjehRez", reze);
		
		if(klasa.equals("ECONOMY")){
			l.setPopunjenostEconomy(l.getPopunjenostEconomy() + 1);
			 
		}
		else {
			l.setPopunjenostBusiness(l.getPopunjenostBusiness()+1);
		
		}
		l.setPopunjenost(l.getPopunjenost()+1);
		lr.save(l);
		
	
		
		
		return "pretraga/search";
	}
	
	
	@RequestMapping(value="pregledRezervacija", method=RequestMethod.GET)
	public String pregledR(HttpServletRequest request) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		Korisnik k=kr.findByKorisnickoIme(currentPrincipalName);
		List<Rezervacija> rezAktuelne=rr.aktuelneRezervacije(k.getIdKorisnik(), new Date());
		
		request.getSession().setAttribute("rezAktuelne", rezAktuelne);
		
		return "rezervacija/pregledAktuelnihRezervacija";
	}
	
	@RequestMapping(value="pregledIstorijeRezervacija", method=RequestMethod.GET)
	public String pregledStarihRez(HttpServletRequest request) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		Korisnik k=kr.findByKorisnickoIme(currentPrincipalName);
		List<Rezervacija> rezProsle=rr.prosleRezervacije(k.getIdKorisnik(), new Date());
		
		request.getSession().setAttribute("rezProsle",rezProsle);
		
		return "rezervacija/pregledStarihRezervacija";
		
	}
		
	
		
	

}
