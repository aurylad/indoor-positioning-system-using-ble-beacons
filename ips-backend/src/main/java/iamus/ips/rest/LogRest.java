package iamus.ips.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import iamus.ips.jpa.entity.BeaconEntity;
import iamus.ips.jpa.entity.LogEntity;
import iamus.ips.jpa.repository.LogRepository;
import io.swagger.annotations.ApiParam;
import iamus.ips.server.api.LogApi;
import iamus.ips.server.model.Log;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class LogRest implements LogApi {

	@Autowired
	private LogRepository logRepository;

	public LogRest() {

		this.logRepository = null;
	}

	@Override
	public ResponseEntity<List<Log>> getLog() {
		final List<Log> list = new ArrayList<>();
		for (final LogEntity src : logRepository.findAllByOrderByIdAsc()) {
			list.add(toLog(src));
		}
		return Utils.toResponseEntity(list);
	}

	@Override
	public ResponseEntity<List<Log>> getLogByObjectId(@ApiParam(value = "Numeric ID of the object to get log data.", //
			required = true) @PathVariable("id") Long id) {
		final List<Log> list = new ArrayList<>();
		System.out.println(list);
		for (final LogEntity src : logRepository.findLogsByObjectId(id)) {
			list.add(toLog(src));
		}
		System.out.println(list);
		return Utils.toResponseEntity(list);
	}

	@Override
	public ResponseEntity<List<Log>> getLogByPlanId(@ApiParam(value = "Numeric ID of the plan to get log data.", //
			required = true) @PathVariable("id") Long id) {
		final List<Log> list = new ArrayList<>();
		System.out.println(list);
		for (final LogEntity src : logRepository.findLogsByPlanId(id)) {
			list.add(toLog(src));
		}
		System.out.println(list);
		return Utils.toResponseEntity(list);
	}

	private static Log toLog(LogEntity src) {
		final Log tgt = new Log();
		tgt.setId(src.getId());
		tgt.setCoordinateX(src.getLogCoordinateX());
		tgt.setCoordinateY(src.getLogCoordinateY());
		tgt.setRegDateTime(src.getLogDateTime());
		tgt.setObjectId(src.getObject().getObjectId());
		tgt.setPlanId(src.getPlan().getPlanId());
		tgt.setObjectAccessLevel(src.getObject().getAccessLevel());
		tgt.setObjectName(src.getObject().getObjName());
		tgt.setObjectType(src.getObject().getObjType());
		return tgt;
	}


}
