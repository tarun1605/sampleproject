Feature: GitHub User Journeys
  As a user, I want to be able to login, see my details, and get a list of my repositories
  As a user, I want to be able to create, edit and view a repository
  As a user, I want to be able to create an Issue, get a list of Issues for my repo, and add / view comments on that Issue.


  Scenario Outline: Get user GitHub details
    Given User makes github GET request to /users/<username> endpoint to get user details
    Then User will get a 200 response
    And The response contains valid user details

    Examples:
      | username     |
      | SachinGunjal |


  Scenario Outline: Get user's list of repositories
    Given User makes github GET request to /users/<username>/repos endpoint to get list of repositories
    Then User will get a 200 response
    And The response contains valid repository details

    Examples:
      | username     |
      | SachinGunjal |