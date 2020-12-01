# Description of work done

## What have I done?
Within the timebox of 2 hours I changed the following:
- corrected found errors/bugs (one not found, see below)
- refactoring of the code according to code quality / practices, including removed some configuration-by-convention uses (table and column names), as one should not always rely on that it works on every environment/platform
- re-ordered package/class structure
- added LunchControllerTest (as @SpringBootTest)
- added a strategy RecipeSortingStrategy for sorting the Recipes, called by LunchService
- added a test for that strategy


## What is still pending / open issues
The code is not yet production ready!

The following is still to be done:

### More bugs to be eliminated

I could not correct the bug in LunchService::loadRecipes function, the Criteria logic to exclude all Recipes with Ingredients with 'use-by' lower than the requested date. I would have needed more time to research as I have not used before Criteria API and Predicate together with Many-To-Many-relations. I changed the 'lessThan' to a 'greaterThan', but than all recipes were returned.

### More robustness

Make the code more robust/tolerant against data inconsistencies, like incomplete data sets.

### More tests

- write tests for the Criteria logic in LunchService including an H2 with test data
- more edge case testing in the strategy test

### Refactoring of the tables/entities:

- use numeric IDs as primary key, not the names (otherwise, the names could not be changed)
- write the column names in lower-case letters
