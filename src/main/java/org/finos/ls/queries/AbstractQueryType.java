package org.finos.ls.queries;

import java.util.function.BiFunction;

import org.finos.scan.github.client.Repository;
import org.finos.scan.github.client.util.QueryExecutor;

public class AbstractQueryType<X> implements QueryType<X> {
	
	private final String fields;
	private final int pageSize;
	private final BiFunction<Repository, QueryExecutor, X> converter;
	
	public AbstractQueryType(String fields, int pageSize, BiFunction<Repository, QueryExecutor, X> converter) {
		super();
		this.fields = fields;
		this.pageSize = pageSize;
		this.converter = converter;
	}

	@Override
	public String getFields() {
		return fields;
	}

	@Override
	public X convert(Repository r, QueryExecutor qe) {
		return converter.apply(r, qe);
	}

	@Override
	public int getPageSize() {
		return pageSize;
	}

}
