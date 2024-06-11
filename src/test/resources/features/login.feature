Feature: Search and place the product

  @Regression
  Scenario Outline: search experience on the both home page and login page


    Given User on the GreenCart landing page
    When  User search for short name <Name> and extract the actual other
    Then I verify "Tomato - 1 Kg" displayed
    And I click on topdeals Button
    And I search the item on the offer page<Name>
    Then Validate landing page productName match with cart page

    Examples:
      | Name |
      | Tom  |
      | Bett |

