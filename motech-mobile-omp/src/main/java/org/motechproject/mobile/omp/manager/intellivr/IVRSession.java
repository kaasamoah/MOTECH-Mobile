package org.motechproject.mobile.omp.manager.intellivr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.motechproject.mobile.core.model.GatewayRequest;

public class IVRSession {

	private String userId;
	private String phone;
	private int attempts;
	private Collection<GatewayRequest> requests;
	
	public IVRSession(String userId, String phone) {
		this.userId = userId;
		this.phone = phone;
		requests = new ArrayList<GatewayRequest>();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	public Object getSessionId() {
		return userId + "-" + phone;
	}

	public void addGatewayRequest(GatewayRequest r) {
		boolean found = false;
		for (Iterator<GatewayRequest> iterator = requests.iterator(); iterator.hasNext();) {
			GatewayRequest gr = iterator.next();
			if ( gr.getId() == r.getId() )
				found = true;
		}
		if ( !found )
			requests.add(r);
	}

	public void removeGatewayRequest(GatewayRequest r) {
		ArrayList<GatewayRequest> toRemove = new ArrayList<GatewayRequest>();
		for ( GatewayRequest gr : requests )
			if ( gr.getId() == r.getId() )
				toRemove.add(gr);
		for ( GatewayRequest gr : toRemove )
			requests.remove(gr);
	}
	
	public Collection<GatewayRequest> getGatewayRequests() {
		return requests;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IVRSession other = (IVRSession) obj;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}