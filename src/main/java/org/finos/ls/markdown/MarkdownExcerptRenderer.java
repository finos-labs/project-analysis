package org.finos.ls.markdown;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.commonmark.node.Image;
import org.commonmark.node.Link;
import org.commonmark.node.ListItem;
import org.commonmark.node.Node;
import org.commonmark.renderer.NodeRenderer;
import org.commonmark.renderer.text.TextContentNodeRendererContext;

public class MarkdownExcerptRenderer implements NodeRenderer {
	
	public MarkdownExcerptRenderer(TextContentNodeRendererContext context) {
		this.context = context;
	}

	private final TextContentNodeRendererContext context;

	@Override
	public Set<Class<? extends Node>> getNodeTypes() {
		List<Class<? extends Node>> classes = Arrays.asList(Image.class, Link.class, ListItem.class);
		return new HashSet<Class<? extends Node>>(classes);
	}

	@Override
	public void render(Node node) {
		if (node instanceof Image) {
			Image i = (Image) node;
			if (hasScheme(i.getDestination())) {
				// only output absolute images
				context.getWriter().write("![");
				renderChildren(node);
				context.getWriter().write("]("+i.getDestination()+") ");
			}
		} else if (node instanceof Link) {
			Link l = (Link) node;
			if (hasScheme(l.getDestination())) {
				// only output absolute links
				context.getWriter().write("[");
				renderChildren(node);
				context.getWriter().write("]("+l.getDestination()+")");
			} else {
				renderChildren(node);
			}
		} else if (node instanceof ListItem) {
			ListItem li = (ListItem) node;
			context.getWriter().line();
			renderChildren(node);
		}
	}

	private boolean hasScheme(String d) {
		return d.startsWith("http") || d.startsWith("mailto");
	}

	
    private void renderChildren(Node parent) {
        Node node = parent.getFirstChild();
        while (node != null) {
            Node next = node.getNext();
            context.render(node);
            node = next;
        }
    }
}
