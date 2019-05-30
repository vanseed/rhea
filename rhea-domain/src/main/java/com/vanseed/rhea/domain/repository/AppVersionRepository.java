package com.vanseed.rhea.domain.repository;

import com.vanseed.rhea.domain.model.AppVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * description
 *
 * @author leon
 * @date 2019/04/09
 */
public interface AppVersionRepository extends JpaRepository<AppVersion, Long> ,JpaSpecificationExecutor<AppVersion>{

    public AppVersion findByAppTypeAndDeviceTypeAndVersion(int appType,int deviceTypes,String version);;

    public AppVersion findByAppTypeAndDeviceTypeAndLatestFlag(int appType,int deviceType,int latestFlag);

    Integer countByAppTypeAndDeviceTypeAndValidFlagAndVersionGreaterThan(int appType,int deviceType,int validFlag,String version);
}
