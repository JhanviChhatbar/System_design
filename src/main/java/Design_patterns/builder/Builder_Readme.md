**Goal of the Pattern**

We have a House object.

The construction logic differs depending on the house type (Igloo, Wooden), but the construction process (foundation → walls → roof) stays the same.

The Director defines the construction order.

Builder interface declares steps.

ConcreteBuilders implement the steps for different house types.

**Summary**

House: the product.

HouseBuilder: defines the steps.

IglooHouseBuilder / WoodenHouseBuilder: implement specific house construction.

CivilEngineer (Director): controls the construction sequence.