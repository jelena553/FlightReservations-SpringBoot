package airline.converter;

import org.springframework.core.convert.converter.Converter;

import airline.repository.AvionRepository;
import model.Avion;

public class AvionKonverter implements Converter<String, Avion> {
	
	AvionRepository ar;
	
	public AvionKonverter(AvionRepository ar) {
		this.ar=ar;
	}

	@Override
	public Avion convert(String source) {
		int avionId=-1;
		
		try {
		avionId=Integer.parseInt(source);
		}
		catch(NumberFormatException e) {
			return null;
		}
		Avion a=ar.findById(avionId).get();
		return a;
	}

}
