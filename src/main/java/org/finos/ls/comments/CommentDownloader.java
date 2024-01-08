package org.finos.ls.comments;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.finos.scan.github.client.Issue;
import org.finos.scan.github.client.IssueComment;
import org.finos.scan.github.client.Repository;
import org.finos.scan.github.client.util.QueryExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graphql_java_generator.exception.GraphQLRequestExecutionException;
import com.graphql_java_generator.exception.GraphQLRequestPreparationException;

@Service
public class CommentDownloader {

	private static final DateFormat DF = new SimpleDateFormat("dd MMM yyyy");
	
	@Autowired
	QueryExecutor qe;
	
	public Issue getIssue(String owner, String repo, int issueNumber) throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		Repository r = qe.repository("""
			{	
			    issue(number: """+issueNumber+"""
			    		) {
			      title
			      body
			      createdAt
			      updatedAt
			      number
			      state
			      author {
			        login
			      }
			      comments: comments(last: 20) {
			        edges {
			          node {
			            body
			            createdAt
			            updatedAt
			            author {
			              login
			            }
			          }
			        }
			      }
			      labels(first: 5) {
			        edges {
			          node {
			            name
			            color
			          }
			        }
			      }
			    }
			   }
		""", null, repo, owner);
	
		return r.getIssue();	
	}
	
	private String outputLabels(Issue i) {
		return i.getLabels().getEdges().stream()
		.map(le -> le.getNode().getName()+" ")
		.reduce("", (a,b) -> a+b);
	}
	
	
	public String formatIssue(Issue i, String owner, String repo) {
		StringBuilder sb = new StringBuilder();
		sb.append("#" + i.getTitle()+"\n\n");		
		sb.append("Owner: "+owner+"\n\n");
		sb.append("Repo: "+repo+"\n\n");
		sb.append("Labels: "+outputLabels(i)+"\n\n");
		
		sb.append(formatComment(i));
		i.getComments().getEdges().forEach(ic -> sb.append(formatComment(ic.getNode())));
		return sb.toString();
	}
	
	private String formatDate(Date d) {
		return DF.format(d);
	}
	
	public String formatComment(IssueComment c) {
		StringBuilder sb = new StringBuilder();		
		sb.append("## "+c.getAuthor().getLogin() + " ("+formatDate(c.getCreatedAt())+")\n\n");
		sb.append(c.getBody());
		sb.append("\n\n");
		return sb.toString();
	}
	
	public String formatComment(Issue c) {
		StringBuilder sb = new StringBuilder();		
		sb.append("## "+c.getAuthor().getLogin() + " ("+formatDate(c.getCreatedAt())+")\n\n");
		sb.append(c.getBody());
		sb.append("\n\n");
		return sb.toString();
	}
}
