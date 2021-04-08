## Supermarkte Checkout Sample Project

This project contains a sample code for a super market checkout with special pricing rules for all/some of
the items.

#### Pricing Rules

Pricing rules has following information
* Item Id
* Normal Unit Price
* Special Offer >>
    * Unit count for eligibility
    * Special Price

#### Assumptions

* For a pricing rule on item A : `Unit Price 60` `3 units for 150`, it is assumed that the remaining units 
after applying special offer are charged with normal unit price.

  Ex: Total of 5 units will cost : `150 + (2 x 60)`
  
#### Execution

* There are no additional requirements to run the code.
* Running the code will create a random checkout basket and out put the total value. 