# Contributing Guidelines

There are two main ways to contribute to the project &mdash; submitting issues and submitting
fixes/changes/improvements via pull requests.

## Issue Submission

We welcome both bug reports and feature requests.
Please submit them [here](https://github.com/Kotlin/kandy/issues).

* To avoid reporting duplicates, please search for existing issues before submitting a new one.
* When submitting a bug report:
    * Test the bug against the most recent version. It may have already been resolved.
    * Include the code that reproduces the issue or provides a link to a repository with a project that can fully
      reproduce it.
    * Don't delay reporting strange or uncommon bugs just because you can't reproduce them consistently.
    * If the bug involves unexpected behavior, explain what you expected to happen and what actually happened.
* When submitting a feature request:
    * Explain why you need the feature, including your use case and domain.
    * It is more important to explain the problem you're facing than to suggest a solution.
      Please report your issue even if you don't have a proposed solution.
    * If there is an alternative way to accomplish what you need, please provide the relevant code.

## Submitting Pull Requests

We welcome Pull Requests.
You can submit them [here](https://github.com/Kotlin/kandy/pulls).
However, please keep in mind that the maintainers will have to support the resulting code of the project,
so do familiarize yourself with the following guidelines:

* All development (including new features and bug fixes) is performed in the `main` branch.
    * Base Pull Requests against the `main` branch.
    * The PR should be linked with the issue, except for minor documentation changes, adding unit tests, and fixing
      typos.
* If you make any code changes:
    * Follow the [Kotlin Coding Conventions](https://kotlinlang.org/docs/reference/coding-conventions.html).
    * [Build the project](#building) to ensure that it works and passes the tests.
* If you fix a bug:
    * Write the test that reproduces the bug.
    * Fixes without tests are accepted only in exceptional circumstances,
      where it can be shown that writing the corresponding test is too hard or otherwise impractical.
* If you introduce any new public APIs:
    * All new APIs must come with documentation and tests.
    * If you plan to add an API, please start by submitting an issue with the proposed API design
      to gather community feedback.
    * [Contact the maintainers](#contacting-maintainers) to coordinate any great work in advance via submitting an
      issue.
* If you fix documentation:
    * If you plan extensive rewrites or additions to the docs, please [contact the maintainers](#contacting-maintainers)
      to coordinate the work in advance.

## PR Workflow

1. Before submitting a pull request, make sure to build the library locally and run all unit tests using
   the Gradle task `kandy:test` (see the ["Building"](#building) section).
2. Submit the pull request only if the local build is successful and all tests pass.
3. Once the pull request is submitted, a reviewer will be assigned to review the changes.
   The reviewer should add their name to the "Reviewers" section of the pull request.
4. The reviewer will either leave comments or mark the pull request with "LGTM"
   (Looks good to me) if they approve the changes.
5. If there are comments, the contributor should address them and make the necessary changes to the pull request.
6. The reviewer will mark the pull request with "LGTM" once all comments are addressed, and they approve the changes.
7. The maintainer may suggest merging the `main` branch to the pull request branch multiple times during the review
   process to incorporate changes from the `main` branch.
8. The maintainer will run integration tests (unit tests and examples) using TC (TeamCity) builds.
9. TC will report the result (pass or fail) of the tests in the checks section at the bottom of the pull request.
10. If there are any failed tests, the maintainer will share the details with the contributor.
11. Once all checks pass, and there are no conflicts with the `main` branch, the maintainer will merge the pull request.

## How to Fix an Existing Issue

If you plan to work on an existing issue, follow these steps:

* Comment on the issue to indicate that you plan to work on it.
* Wait until the maintainers assign the issue to you.
* Ensure that the issue describes a problem and a solution that has received positive feedback.
  If there isn't a proposed solution, propose one.

If you plan to submit your first PR in this project:

* Look for issues labeled
  as ["good first issue"](https://github.com/Kotlin/kandy/issues?q=is%3Aissue+is%3Aopen+label%3A%22good+first+issue%22+no%3Aassignee)
  that haven't been assigned to anyone.
* Explore the [`examples`](https://github.com/Kotlin/kandy/tree/main/examples) module.
  Submit a new example or improve the documentation for an existing one.

If you're interested in participating in library design and new experiments:

* Look for issues labeled as ["enhancement"](https://github.com/Kotlin/kandy/labels/enhancement),
  or join our [discussions](https://github.com/Kotlin/kandy/discussions).

## Environment Requirements

The JDK version should be equal to or greater than 1.8, and it should be referred to by the `JAVA_HOME` environment
variable.

## Building

To build this library, you can use Gradle.

* Run `./gradlew build` to build the library and run all the tests.
* Run `./gradlew <module>:test` to test a specific module, which can speed up development.

You can also import this project into IntelliJ IDEA, but make sure to delegate the build actions to Gradle.
To do this, go to `Preferences` -> `Build, Execution, Deployment` -> `Build Tools` -> `Gradle` -> `Runner`.

## Contacting maintainers

If you encounter any issues or have questions about the project, there are several ways to contact the maintainers:

* Submit an [issue](#issue-submission) on GitHub to report a bug or request a new feature.
* Mention one of the maintainers (@AndreiKingsley, @devcrocod) on GitHub to draw their attention
  to a specific problem or ask a question.
* For general inquiries and discussions, use the `#datascience` channel on
  the [KotlinLang Slack](https://kotl.in/slack).
