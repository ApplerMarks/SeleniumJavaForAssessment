Feature: Datacom website - Contact us page

	@Section
  Scenario Outline: Verify Contact us page anchor navigation to specified section
    Given the user navigates to Datacom contact us page
    When the user click the "<Section>" link
    Then the page will scroll down to that "<Section>"

    Examples: 
      | Section       |
      | Our locations |
      | Get in touch  |
      | Media pack    |

	@CityOffice
  Scenario Outline: Verify Contact us page Our location section office locations
    Given the user navigates to Datacom contact us page
    When the user click the Our location anchor tag
    And clicks the "<Country>" and also clicks the "<City>"
    Then the "<City>" office details will show

    Examples: 
      | Country     | City        |
      | New Zealand | Auckland    |
      | New Zealand | Dunedin     |
      | New Zealand | Wellington  |
      | Australia   | Adelaide    |
      | Australia   | Brisbane    |
      | Australia   | Canberra    |
      | Asia        | Philippines |
      | Asia        | Malaysia    |
      | Asia        | Singapore   |
