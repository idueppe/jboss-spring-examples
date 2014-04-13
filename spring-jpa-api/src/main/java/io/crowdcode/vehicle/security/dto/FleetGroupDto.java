package io.crowdcode.vehicle.security.dto;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class FleetGroupDto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String companyName;
    private List<UserDto> users;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<UserDto> getUsers() {
        if (users == null) {
            users = new LinkedList<>();
        }
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
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
        FleetGroupDto other = (FleetGroupDto) obj;
        if (companyName == null) {
            if (other.companyName != null)
                return false;
        } else if (!companyName.equals(other.companyName))
            return false;
        return true;
    }

}
