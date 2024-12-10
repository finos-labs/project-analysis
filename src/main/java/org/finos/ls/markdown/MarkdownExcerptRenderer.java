package org.finos.ls.markdown;

import org.commonmark.node.BlockQuote;
import org.commonmark.node.BulletList;
import org.commonmark.node.Code;
import org.commonmark.node.Document;
import org.commonmark.node.FencedCodeBlock;
import org.commonmark.node.HardLineBreak;
import org.commonmark.node.Heading;
import org.commonmark.node.HtmlBlock;
import org.commonmark.node.HtmlInline;
import org.commonmark.node.Image;
import org.commonmark.node.IndentedCodeBlock;
import org.commonmark.node.Link;
import org.commonmark.node.ListItem;
import org.commonmark.node.Node;
import org.commonmark.node.OrderedList;
import org.commonmark.node.Paragraph;
import org.commonmark.node.SoftLineBreak;
import org.commonmark.node.ThematicBreak;
import org.commonmark.renderer.text.CoreTextContentNodeRenderer;
import org.commonmark.renderer.text.TextContentNodeRendererContext;

public class MarkdownExcerptRenderer extends CoreTextContentNodeRenderer {
	
	enum State { NEW, EMPTY_LINE, WRITTEN_LINE }
	
	private int amountToWrite;
	private State state = State.NEW;
	private final String url;
	
	public MarkdownExcerptRenderer(TextContentNodeRendererContext context, int amountToWrite, String url) {
		super(context);
		this.amountToWrite = amountToWrite;
		this.url = url;
	}

	@Override
	public void visit(Image i) {
		if (hasScheme(i.getDestination())) {
			// only output absolute images
			context.getWriter().write("![");
			renderChildren(i);
			context.getWriter().write("]("+i.getDestination()+") ");
		}
	}
	
	public void visit(Link l) {
		if (hasScheme(l.getDestination())) {
			// only output absolute links
			context.getWriter().write("[");
			renderChildren(l);
			context.getWriter().write("]("+l.getDestination()+")");
		} else {
			renderChildren(l);
		}
	}
	
	@Override
	public void visit(BulletList bulletList) {
		if (bulletList.getParent() instanceof Document) {
			if (amountToWrite > 0) {
				amountToWrite--;
				state = State.WRITTEN_LINE;
				super.visit(bulletList);
				checkAddReadMore();
			}
		} else {
			super.visit(bulletList);
		}
	}
	
	@Override
	public void visit(ListItem listItem) {
		addQuote();
		super.visit(listItem);
	}

	@Override
	public void visit(OrderedList orderedList) {
		if (orderedList.getParent() instanceof Document) {
			if (amountToWrite > 0) {
				amountToWrite--;
				state = State.WRITTEN_LINE;
				super.visit(orderedList);
				checkAddReadMore();
			}
		} else {
			super.visit(orderedList);
		}
	}

	@Override
	public void visit(Paragraph paragraph) {
		if (paragraph.getParent() instanceof Document) {
			if (amountToWrite > 0) {
				amountToWrite--;
				addQuote();
				state = State.WRITTEN_LINE;
				super.visit(paragraph);
				checkAddReadMore();
			}
		} else {
			super.visit(paragraph);
		}
	}

	private void checkAddReadMore() {
		if (amountToWrite > 0) {
			addQuote();
			context.getWriter().write("\n");
		} else {
			context.getWriter().write(">... [_read more_]("+url+")");
		}
	}

	@Override
	public void visit(HardLineBreak hardLineBreak) {
	}

	private void addQuote() {
		context.getWriter().write("> ");
	}

	@Override
	public void visit(SoftLineBreak softLineBreak) {
		super.visit(softLineBreak);
		addQuote();
	}

	@Override
	public void visit(BlockQuote blockQuote) {
		handleLineSpace();
	}

	private void handleLineSpace() {
		if (state==State.WRITTEN_LINE) {
			context.getWriter().line();
			addQuote();
			context.getWriter().line();
			state = State.EMPTY_LINE;
		}
	}

	@Override
	public void visit(Code code) {
		context.getWriter().write("`");
		super.visit(code);
		context.getWriter().write("`");
	}

	@Override
	public void visit(FencedCodeBlock fencedCodeBlock) {
		handleLineSpace();
	}

	@Override
	public void visit(Heading heading) {
		handleLineSpace();
	}

	@Override
	public void visit(ThematicBreak thematicBreak) {
		handleLineSpace();
	}
	
	

//	@Override
//	public void visit(Emphasis emphasis) {
//		super.visit(emphasis);
//	}
//
//	@Override
//	public void visit(StrongEmphasis strongEmphasis) {
//		super.visit(strongEmphasis);
//	}
//
	@Override
	public void visit(HtmlInline htmlInline) {
		handleLineSpace();
	}

	@Override
	public void visit(HtmlBlock htmlBlock) {
		handleLineSpace();
	}

	@Override
	public void visit(IndentedCodeBlock indentedCodeBlock) {
		handleLineSpace();
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
