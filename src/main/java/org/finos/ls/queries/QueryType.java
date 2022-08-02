package org.finos.ls.queries;

import org.finos.scan.github.client.Repository;

public interface QueryType<X> {
	
	public String getFields();

	public X convert(Repository r);
	
	public int getPageSize();
}
