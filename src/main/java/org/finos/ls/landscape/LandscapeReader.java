package org.finos.ls.landscape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.yaml.snakeyaml.Yaml;

@Component
public class LandscapeReader {
	

	@Value("${landscapeLogoPrefix:https://raw.githubusercontent.com/finos/finos-landscape/master/hosted_logos/}")
	String logoPrefix;

	public List<ProjectInfo> readFromLandscape(String url) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.TEXT_PLAIN));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

		if (response.getStatusCode().equals(HttpStatus.OK)) {

			Yaml yaml = new Yaml();
			Map<String, Object> data = yaml.load(response.getBody());
			return readCategories((List<Map<String, Object>>) data.get("landscape"));
		}

		return null;
	}

	public List<ProjectInfo> readCategories(List<Map<String, Object>> data) {
		List<ProjectInfo> out = new ArrayList<>();

		for (Map<String, Object> object : data) {
			String categoryName = (String) object.get("name");
			List<Map<String, Object>> subcats = (List<Map<String, Object>>) object.get("subcategories");
			for (Map<String, Object> subcat : subcats) {
				String subCategoryName = (String) subcat.get("name");
				List<Map<String, Object>> items = (List<Map<String, Object>>) subcat.get("items");
				for (Map<String, Object> item : items) {
					ProjectInfo pi = new ProjectInfo();
					pi.category = categoryName;
					pi.subcategory = subCategoryName;
					pi.logo = this.logoPrefix +  (String) item.get("logo");
					pi.name = (String) item.get("name");
					pi.mainRepo = (String) item.get("repo_url");
					pi.type = ProjectInfo.projectType((String) item.get("project"));
					pi.homepageUrl = (String) item.get("homepage_url");
					List<Map<String, Object>> additionalRepos = (List<Map<String, Object>>) item.get("additional_repos");
					if (additionalRepos != null) {
						List<String> ar = additionalRepos.stream().map(i -> (String) i.get("repo_url"))
								.collect(Collectors.toList());
						pi.additionalRepos = ar;
					}
					out.add(pi);
				}
			}
		}

		return out;
	}
}
