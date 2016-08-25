package com.usermangement.model;

import java.util.Date;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import org.apache.commons.lang3.StringUtils;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class User {

	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private Address address;
	private Date dateCreated;
	private Company company;
	private String profilePic;

	public boolean isValid() {
		// return id != null && !id.isEmpty();
		return StringUtils.isNotBlank(id) && StringUtils.isNotBlank(firstName)
				&& StringUtils.isNotBlank(lastName)
				&& StringUtils.isNotBlank(email)
				&& StringUtils.isNotBlank(profilePic) && dateCreated != null
				&& company != null && address != null;
	}

}
