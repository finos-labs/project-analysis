package org.finos.ls.queries;

import java.util.function.Function;

import org.finos.scan.github.client.Repository;

public class AbstractQueryType<X> implements QueryType<X> {
	
	private final String fields;
	private final int pageSize;
	private final Function<Repository, X> converter;
	
	public AbstractQueryType(String fields, int pageSize, Function<Repository, X> converter) {
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
	public X convert(Repository r) {
		return converter.apply(r);
	}

	@Override
	public int getPageSize() {
		return pageSize;
	}

}
