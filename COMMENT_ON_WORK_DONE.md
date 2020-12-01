# Description of work done

## What have I done?
Within the timebox of 2 hours I changed the following:
- corrected found errors/bugs
- I could not correct the bug in the Criteria API logic to exclude all Recipes with Ingredients with useBy lower than the requested date, need more time to research as I have not used Criteria API and Predicate together with Many-To-Many-relations.
- refactor code according to code quality / practices, including removed some configuration-by-convention uses (table and column names), as one should not always rely on that it works on every environment/platform
- re-ordered package/class structure
- added LunchControllerTest (as @SpringBootTest)
- added a strategy RecipeSortingStrategy for sorting the Recipes, called by LunchService
- added a test for that strategy


## What is still pending / open issues
The code is not yet production ready!

The following is still to be done:

### More Tests
- write tests for the Criteria logic in LunchService including an H2 with test data
- more edge case testing in the strategy test

### Refactor the tables/Entities:
- use numeric IDs as primary key, not the names (otherwise, the names could not be changed)
- write the column names in lower-case letters

### Format the JSON output
- format the JSON output a bit better regarding the LocalDates, use a JSON CustomSerializer