package iamus.ips.jpa.repository;
import iamus.ips.jpa.entity.LogEntity;

import java.util.List;

public interface LogRepositoryCustom {

	List<LogEntity> findLogsByPlanId (Long planId);
	List<LogEntity> findLogsByObjectId (Long objectId);
}