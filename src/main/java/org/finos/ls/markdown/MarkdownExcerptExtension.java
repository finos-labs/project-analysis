package org.finos.ls.markdown;

import org.commonmark.renderer.NodeRenderer;
import org.commonmark.renderer.text.TextContentNodeRendererContext;
import org.commonmark.renderer.text.TextContentNodeRendererFactory;
import org.commonmark.renderer.text.TextContentRenderer;
import org.commonmark.renderer.text.TextContentRenderer.Builder;

public class MarkdownExcerptExtension implements TextContentRenderer.TextContentRendererExtension {

	private String url;
	
	private MarkdownExcerptExtension(String url) {
		this.url = url;
	}
	
	public static MarkdownExcerptExtension create(String url) {
		return new MarkdownExcerptExtension(url);
	}
	
	@Override
	public void extend(Builder rendererBuilder) {
		rendererBuilder.nodeRendererFactory(new TextContentNodeRendererFactory() {
            @Override
            public NodeRenderer create(TextContentNodeRendererContext context) {
                return new MarkdownExcerptRenderer(context, 3, url);
            }
        });
	}
}
