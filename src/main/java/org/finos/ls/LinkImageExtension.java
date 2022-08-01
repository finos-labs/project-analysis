package org.finos.ls;

import org.commonmark.renderer.NodeRenderer;
import org.commonmark.renderer.text.TextContentNodeRendererContext;
import org.commonmark.renderer.text.TextContentNodeRendererFactory;
import org.commonmark.renderer.text.TextContentRenderer;
import org.commonmark.renderer.text.TextContentRenderer.Builder;

public class LinkImageExtension implements TextContentRenderer.TextContentRendererExtension {

	private LinkImageExtension() {
	}
	
	public static LinkImageExtension create() {
		return new LinkImageExtension();
	}
	
	@Override
	public void extend(Builder rendererBuilder) {
		rendererBuilder.nodeRendererFactory(new TextContentNodeRendererFactory() {
            @Override
            public NodeRenderer create(TextContentNodeRendererContext context) {
                return new LinkImageRenderer(context);
            }
        });
	}
}
