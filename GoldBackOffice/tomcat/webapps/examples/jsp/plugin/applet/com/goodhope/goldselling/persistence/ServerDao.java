package com.goodhope.goldselling.persistence;

import java.util.List;

import com.goodhope.goldselling.domain.Country;
import com.goodhope.goldselling.domain.Server;

public interface ServerDao {
	public List<Server> getAllServers();

	public Server getServer(String country, String servername, String faction);

	public Country getCountry(String country);
}
