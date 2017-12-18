package com.ocean.springboot.data.entity.projection;

import com.ocean.springboot.data.enums.RoleType;

public interface UserSummaryProjection 
{
	Long getUserId();
	String getFirstName();
	String getLastName();
	String getUserName();
	String getEmail();
	RoleType getRole(); 
}