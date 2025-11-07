package org.finos.ls.readme;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for mapping project names to calendar event keywords.
 * Loaded from project-mappings.yml via @ConfigurationProperties.
 */
@Configuration
@ConfigurationProperties(prefix = "calendar")
public class ProjectMappingsConfig {

	/**
	 * Map of project names to lists of keywords that should match calendar events.
	 * Example:
	 * projects:
	 * "AI Readiness": ["AI Governance", "GenAI", "LLM"]
	 * "DevOps Automation": ["DevOps", "Dev Ops"]
	 */
	private Map<String, List<String>> projects = new HashMap<>();

	public Map<String, List<String>> getProjects() {
		return projects;
	}

	public void setProjects(Map<String, List<String>> projects) {
		this.projects = projects;
	}
}
