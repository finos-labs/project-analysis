package org.finos.ls;

import java.util.List;

import org.finos.ls.landscape.LandscapeReader;
import org.finos.ls.landscape.ProjectInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = LandscapeApp.class)
@ActiveProfiles("local")
public class LandscapeReaderTest {

	@Autowired
	LandscapeReader lr;
	
	@Test
	public void testReader() {
		List<ProjectInfo> out = lr.readFromLandscape("https://raw.githubusercontent.com/finos/finos-landscape/master/landscape.yml");
		System.out.println(out);
	}
}
