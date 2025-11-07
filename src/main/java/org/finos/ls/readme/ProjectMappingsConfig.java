package org.finos.ls.readme;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.yaml.snakeyaml.Yaml;

/**
 * Configuration for mapping project names to calendar event keywords.
 * Manually loaded from application.yml to preserve exact key names (including
 * spaces).
 */
@Configuration
public class ProjectMappingsConfig {

	@Bean
	@SuppressWarnings("unchecked")
	public Map<String, List<String>> projectMappings() {
		try {
			Yaml yaml = new Yaml();
			ClassPathResource resource = new ClassPathResource("application.yml");

			try (InputStream inputStream = resource.getInputStream()) {
				Map<String, Object> yamlData = yaml.load(inputStream);

				// Navigate to calendar.projects
				if (yamlData.containsKey("calendar")) {
					Map<String, Object> calendar = (Map<String, Object>) yamlData.get("calendar");
					if (calendar.containsKey("projects")) {
						Map<String, List<String>> projects = (Map<String, List<String>>) calendar.get("projects");
						System.out.println("Loaded project mappings with keys: " + projects.keySet());
						return projects;
					}
				}
			}
		} catch (Exception e) {
			System.err.println("Error loading project mappings: " + e.getMessage());
			e.printStackTrace();
		}

		// Return empty map if loading fails
		return new HashMap<>();
	}
}
