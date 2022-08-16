package org.finos.ls.markdown;

import org.commonmark.node.BlockQuote;
import org.commonmark.node.BulletList;
import org.commonmark.node.Code;
import org.commonmark.node.FencedCodeBlock;
import org.commonmark.node.HardLineBreak;
import org.commonmark.node.HtmlBlock;
import org.commonmark.node.HtmlInline;
import org.commonmark.node.Image;
import org.commonmark.node.IndentedCodeBlock;
import org.commonmark.node.Link;
import org.commonmark.node.ListItem;
import org.commonmark.node.OrderedList;
import org.commonmark.node.SoftLineBreak;
import org.commonmark.node.ThematicBreak;
import org.commonmark.renderer.text.CoreTextContentNodeRenderer;
import org.commonmark.renderer.text.TextContentNodeRendererContext;

public class MarkdownHeadingRenderer extends CoreTextContentNodeRenderer {
		
	public MarkdownHeadingRenderer(TextContentNodeRendererContext context) {
		super(context);
	}

	@Override
	public void visit(Image i) {
	}
	
	public void visit(Link l) {
	}
	
	@Override
	public void visit(BulletList bulletList) {
	}
	
	@Override
	public void visit(ListItem listItem) {
	}

	@Override
	public void visit(OrderedList orderedList) {
	}

	@Override
	public void visit(HardLineBreak hardLineBreak) {
	}

	@Override
	public void visit(SoftLineBreak softLineBreak) {
	}

	@Override
	public void visit(BlockQuote blockQuote) {
	}

	@Override
	public void visit(Code code) {
	}

	@Override
	public void visit(FencedCodeBlock fencedCodeBlock) {
	}

	@Override
	public void visit(ThematicBreak thematicBreak) {
	}

	@Override
	public void visit(HtmlInline htmlInline) {
	}

	@Override
	public void visit(HtmlBlock htmlBlock) {
	}

	@Override
	public void visit(IndentedCodeBlock indentedCodeBlock) {
	}

}
