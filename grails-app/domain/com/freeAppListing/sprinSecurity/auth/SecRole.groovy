package com.freeAppListing.sprinSecurity.auth

class SecRole {

	String authority

	static mapping = {
		cache true
        datasource "trans"
	}

	static constraints = {
		authority blank: false, unique: true
	}
}
