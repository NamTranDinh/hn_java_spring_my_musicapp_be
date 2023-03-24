package com.aptech.mymusic.domain.repository;

import com.aptech.mymusic.domain.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
