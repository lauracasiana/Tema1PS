package com.teatrulnational.services;

import java.util.ArrayList;

import com.teatrulnational.database.TicketDAO;
import com.teatrulnational.models.Bilet;

public class TicketManager {

	public boolean addTicket(Bilet b) {
		TicketDAO td = new TicketDAO();
		boolean ok = td.addTicket(b);
		return ok;
	}

	public ArrayList<Bilet> seeAllTickets() {
		TicketDAO td = new TicketDAO();
		ArrayList<Bilet> bilete = td.seeAllTickets();
		return bilete;
	}
}
