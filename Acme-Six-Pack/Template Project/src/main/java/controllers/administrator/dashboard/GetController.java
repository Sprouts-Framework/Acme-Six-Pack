package controllers.administrator.dashboard;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.CommentService;
import es.us.lsi.dp.controllers.common.AbstractGetController;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;

@Controller("dashboardGetController")
@RequestMapping(value={"dashboard/administrator/list","dashboard/administrator/{context}/list"})
public class GetController extends AbstractGetController implements AddsToModel{

	@Autowired
	private CommentService commentService;
	
	@Override
	protected String view() {
		return "dashboard/list";
	}

	@Override
	public void addToModel(Map<String, Object> objects, List<String> context) {
		if(context.size() >0){
			Integer option = new Integer(context.get(0));

			Double averageOfComments = null;
			Double standardDeviationOfComments = null;
			Double averageNumberOfCommentsPerGym = null;
			Double averageNumberOfCommentsPerService = null;
			
			switch(option){
			case 10:
				Object[] queryResult;
				queryResult = commentService.averageAndStantadrdDeviationOfComments();
				averageOfComments = (Double) queryResult[0];
				standardDeviationOfComments = (Double) queryResult[1];
				break;
			case 11:
				averageNumberOfCommentsPerGym = commentService.averageNumberOfCommentsPerGym();
				break;
			case 12:
				averageNumberOfCommentsPerService = commentService.averageNumberOfCommentsPerService();
				break;
			default:
				break;
			}
			
			objects.put("averageOfComments", averageOfComments);		
			objects.put("standardDeviationOfComments", standardDeviationOfComments);	
			
			objects.put("averageNumberOfCommentsPerGym",averageNumberOfCommentsPerGym);
			objects.put("averageNumberOfCommentsPerService",averageNumberOfCommentsPerService);
			
			objects.put("option", option);
		}
	}
	
	
}
