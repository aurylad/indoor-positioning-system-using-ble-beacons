package iamus.ips.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import iamus.ips.jpa.entity.ViolationsEntity;
import iamus.ips.jpa.repository.ViolationsRepository;
import iamus.ips.server.api.ViolationsApi;
import iamus.ips.server.model.Violations;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class ViolationRest implements ViolationsApi {

	@Autowired
	private ViolationsRepository violationsRepository;

	public ViolationRest() {
		this.violationsRepository = null;
	}

	@Override
	public ResponseEntity<List<Violations>> getViolation() {
		final List<Violations> list = new ArrayList<>();
		for (final ViolationsEntity src : violationsRepository.findAllByOrderByIdAsc()) {
			list.add(toViolation(src));
		}
		return Utils.toResponseEntity(list);
	}

	private static Violations toViolation(ViolationsEntity src) {
		final Violations tgt = new Violations();
		tgt.setId(src.getId());
		tgt.setObjectAccessLevel(src.getObject().getAccessLevel());
		tgt.setObjectCode(src.getObject().getObjectCode());
		tgt.setObjectName(src.getObject().getObjName());
		tgt.setObjectType(src.getObject().getObjType());
		tgt.setPlanName(src.getRestrictedArea().getPlan().getPlanId());
		tgt.setRestrictedArea(src.getRestrictedArea().getRestrictedAreaName());
		tgt.setViolationDateTime(src.getViolationDateTime());
		return tgt;
	}

}
