package airline.controller;

import java.sql.SQLIntegrityConstraintViolationException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.IllegalStateException;

@ControllerAdvice
@RequestMapping("excep")
public class ExceptionController {
	
	@ExceptionHandler(value=ConstraintViolationException.class)
	public String greska(RedirectAttributes model) {
		model.addFlashAttribute("duplikat", 1);
		return "redirect:/auth/metod1";
	}
	
	

}
