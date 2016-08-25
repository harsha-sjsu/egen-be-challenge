package com.usermangement.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Address {
	private String street;
    private String city;
    private String zip;
    private String state;
    private String country;
}
