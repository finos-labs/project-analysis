![badge-labs](https://user-images.githubusercontent.com/327285/230928932-7c75f8ed-e57b-41db-9fb7-a292a13a1e58.svg)

# Project Analysis

Project Analysis is a tool that scan the FINOS (Fintech Open Source Foundation) GitHub repositories and:
- Generates a readme of readme's, which can be used as a landing page for GitHub Organizations.
- Builds a project health report (in CSV format) that can be used to analyze the health of the projects.

## Features

- **Project Activity Analytics**: Gather and analyze data about project activity, including commit frequency, issue tracking, and pull request statistics.
- **README Summarization**: Automatically generate summaries for project README files to provide a quick overview of each project.
- **Visualization**: Generate visual representations of project activity and README summaries for better insights.

## Installation

1. **Clone the repository**:
    ```sh
    git clone https://github.com/finos-labs/project-analysis.git
    cd project-analysis
    ```

### Scanning the FINOS Project Landscape

To scan the FINOS project landscape and gather data, run:
```sh
  LANDSCAPE_SCANNING_TOKEN={your-github-personal-access-token} mvn spring-boot:run -Dspring-boot.run.profiles=local,summarize -DskipTests
```
#### Generating a Personal Access Token

To generate a personal access token, follow the instructions [here](https://docs.github.com/en/github/authenticating-to-github/creating-a-personal-access-token).

The PAT must have the following scopes enabled:
- Under `repo` the `public_repo` scope must be enabled.
- Under  `adimin:org` the `read:org` scope must be enabled.
- Under `project` the `project:read` scope must be enabled.

## Contributing

1. Fork it (<https://github.com/finos-labs/project-analysis/fork>)
2. Create your feature branch (`git checkout -b feature/fooBar`)
3. Read our [contribution guidelines](.github/CONTRIBUTING.md) and [Community Code of Conduct](https://www.finos.org/code-of-conduct)
4. Commit your changes (`git commit -am 'Add some fooBar'`)
5. Push to the branch (`git push origin feature/fooBar`)
6. Create a new Pull Request

## License

Copyright 2024 FINOS

Distributed under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).

SPDX-License-Identifier: [Apache-2.0](https://spdx.org/licenses/Apache-2.0)
