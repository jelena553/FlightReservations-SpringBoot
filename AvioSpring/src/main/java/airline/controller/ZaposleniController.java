package airline.controller;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import airline.repository.*;
import model.Aerodrom;
import model.Avion;
import model.Let;
import model.Linija;


@Controller
@RequestMapping(value="radnik")
public class ZaposleniController {
	
		@Autowired
		LetRepository lr;
		
		@Autowired
		LinijaRepository lnr;
		
		@Autowired
		AerodromRepository ar;
		
		@Autowired
		AvionRepository av;
		
		
		@InitBinder
		public void initBinder(WebDataBinder binder) {
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    sdf.setLenient(true);
		    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
		}
		
		@RequestMapping(value="sacuvajLiniju", method=RequestMethod.POST)
		public String sacuvajLiniju (String od, String do1, String poAer,String doAer,
				String poVrijeme, String doVrijeme, Integer cijenaE, Integer cijenaB, Model m)throws Exception {
			
			Linija nova=new Linija();
			nova.setPolaziste(od);
			nova.setOdrediste(do1);
			Aerodrom aer1=ar.findByKod(poAer);
			Aerodrom aer2=ar.findByKod(doAer);
			nova.setAerodrom1(aer1);
			nova.setAerodrom2(aer2);
			
			nova.setCijenaBusiness(cijenaB);
			nova.setCijenaEconomy(cijenaE);
			
			SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
			Date vrijemeD1=(Date)sdf.parse(poVrijeme);
			Date vrijemeD2=(Date)sdf.parse(doVrijeme);
			Time vrijemeT1=new Time(vrijemeD1.getTime());
			Time vrijemeT2=new Time(vrijemeD2.getTime());
			
			nova.setVrijemePolaska(vrijemeT1);
			nova.setVrijemeDolaska(vrijemeT2);
			
			Linija l=lnr.save(nova);
			m.addAttribute("linijaSac", l);
			
			if(l==null)
				m.addAttribute("greskaSaveLinija", true);
			
			return "zaposleni/pocetnaZaposleni";
		}
		
		@RequestMapping(value="ucitajAerodrome", method=RequestMethod.GET)
		String ucitajAerodrome(Model m) {
			List<Aerodrom> aerodromi=ar.findAll();
			m.addAttribute("aerodromi", aerodromi);
			
			return "/zaposleni/unesiLiniju";
		}
		
		@RequestMapping(value="ucitajAvione", method=RequestMethod.GET)
		String ucitajA(HttpServletRequest request) {
			List<Avion> avioni=av.findAll();
			request.getSession().setAttribute("avioni", avioni);
			
		    return "/zaposleni/unesiLet";
		}

		
		
		
		@RequestMapping(value="pronadjiLinije", method=RequestMethod.GET)
		String pronadjiLinije(String polaziste, String odrediste, Model m) {
			List<Linija> linije=lnr.nadjiLinije(odrediste, polaziste);
			m.addAttribute("linije", linije);
			return "/zaposleni/PrikazLinijaPretragaPoOdDo";
			
			
		}
		
		@RequestMapping(value="preusmjeri", method=RequestMethod.GET)
		String preusmjeri(Integer idLinija, HttpServletRequest request) {
			Linija l=lnr.findById(idLinija).get();
			request.getSession().setAttribute("linija", l);
			
			return "/zaposleni/DodajLetDatumi";
		}
		
		
			
			
		
		@ModelAttribute("let")
		public Let napraviLet(){
			return new Let();
		}
        
		
		@RequestMapping(value="unesiLet", method=RequestMethod.POST)
		String unesiLet(@ModelAttribute("let")Let novi, HttpServletRequest request, Model m) throws Exception {
			Linija l=(Linija)request.getSession().getAttribute("linija");
			
			
			/*SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		     datumL=sdf.parse(datumLeta);*/
			if(novi.getDatumPolaska().before(new Date())) {
				
			m.addAttribute("pogresanDatum", true);
			
			return "/zaposleni/DodajLetDatumi";
			}
		
			novi.setLinija(l);
			
			Let letNovi=lr.save(novi);
			m.addAttribute("letS", letNovi);
			
			if(letNovi==null) {
				m.addAttribute("greskaSaveLet", true);
			}
			
			
			return "/zaposleni/DodajLetDatumi";
			
			
		}
		
		

	}



