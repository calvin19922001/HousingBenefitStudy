package hk.gov.housingauthority.nhs.framework.core.model;


import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Base class for Model objects.  Child objects should implement toString(),
 * equals() and hashCode();
 * <p/>
 * <p/>
 * <a href="BaseObject.java.html"><i>View Source</i></a>
 * </p>
 */
public abstract class BaseObject implements Serializable {
//    public boolean equals(Object o) {
//        return EqualsBuilder.reflectionEquals(this, o);
//    }
//
//    public int hashCode() {
//        return HashCodeBuilder.reflectionHashCode(this);
//    }

	private int pageNo;

    private int pageSize;
	
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
    
    
    
    
    
}
