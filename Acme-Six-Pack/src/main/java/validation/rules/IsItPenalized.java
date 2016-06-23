package validation.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import domain.Customer;
import domain.Message;
import es.us.lsi.dp.services.CustomerService;
import es.us.lsi.dp.utilities.Moment;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Component
public class IsItPenalized implements BusinessRule<Message> {

	@Autowired
	private CustomerService customerService;
	
	@Override
	public boolean rule(Message domainObject) {
		
		boolean aux;
		
		Customer c = customerService.findByPrincipal();
		if(c.getEndOfPenalty()==null){
			aux = true;
		}else{
			aux = Moment.now().after(c.getEndOfPenalty());
		}
		return aux;
	}

	@Override
	public String warning() {
		return "message.penalty";
	}

}
