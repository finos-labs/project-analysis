package org.finos.ls.queries;

import org.finos.scan.github.client.Repository;
import org.finos.scan.github.client.util.QueryExecutor;

public interface QueryType<X> {
	
	public String getFields();

	public X convert(Repository r, QueryExecutor qe);
	
	public int getPageSize();
}
