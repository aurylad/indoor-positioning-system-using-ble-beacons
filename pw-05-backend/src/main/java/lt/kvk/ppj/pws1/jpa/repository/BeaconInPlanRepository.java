package lt.kvk.ppj.pws1.jpa.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import lt.kvk.ppj.pws1.jpa.entity.BeaconEntity;
import lt.kvk.ppj.pws1.jpa.entity.BeaconInPlanEntity;

@RepositoryRestResource(path = "beacons-in-plan", exported = false)
public interface BeaconInPlanRepository extends PagingAndSortingRepository<BeaconInPlanEntity, Long> {

	Iterable<BeaconInPlanEntity> findAllByOrderByIdAsc();
	BeaconInPlanEntity findOneByBeaconId(@Param("beaconId") String beaconId);
	
}
