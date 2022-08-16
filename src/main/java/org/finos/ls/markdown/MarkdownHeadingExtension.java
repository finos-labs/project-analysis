package org.finos.ls.markdown;

import org.commonmark.renderer.NodeRenderer;
import org.commonmark.renderer.text.TextContentNodeRendererContext;
import org.commonmark.renderer.text.TextContentNodeRendererFactory;
import org.commonmark.renderer.text.TextContentRenderer;
import org.commonmark.renderer.text.TextContentRenderer.Builder;

public class MarkdownHeadingExtension implements TextContentRenderer.TextContentRendererExtension {

	private MarkdownHeadingExtension() {
	}
	
	public static MarkdownHeadingExtension create() {
		return new MarkdownHeadingExtension();
	}
	
	@Override
	public void extend(Builder rendererBuilder) {
		rendererBuilder.nodeRendererFactory(new TextContentNodeRendererFactory() {
            @Override
            public NodeRenderer create(TextContentNodeRendererContext context) {
                return new MarkdownHeadingRenderer(context);
            }
        });
	}
}
