# Tests for DSA-Tasks Summer 2020
This repository contains unit tests for the tasks in DSA in summer semester 2020.\
**NOTE:** There is no solution code but just the tests; These also aren't solutions to the non-implementation tasks

## Execution
- All sheets are specified in the maven config to be built and executed using jdk11
- To execute the code you must add the classes with the correct names and methods as given in the tasks on ILIAS
- The projects won't compile in the state provided, as the classes which are tested are missing. Use the ones provided
- For some classes there are some methods are provided which are needed for testing. Add them to your implementation


## General notes
- All tests were created for testing our own implementation. They aren't guaranteed to be complete or correct. 
- The implementations were adjusted after we got our correction and the tests made sure to to comple successfully
- All implementations (with a few execptions noted below) were removed from this repository. This is not a solutions repository
- Some classes might have full test coverage while other methods/classes aren't tested or aren't tested at all. We did the best we could
- This repository also includes a visualizer for sorting algorithms. Anyone who finds out how to use it is free to do so. No guarantees/warranty/etc. 
- Everythng you do with the content of this if fully your responsibility The authors of this repository can't be held responsible for any consequences resulting from the usage or distribution of this repository and its content by others

## Notes for specific sheets
- **Sheet 3, Task 3**: The class `BinarySearchTree` needs the variable `nullNode` (the used null node of the tree), the query `size()` returning the number of elements in the tree and the methods `toString()` and `toStringPreorder()` in the way they are alredy implemented in the class
- **Sheet 4, Task 3**: The class `BinarySearchTree` needs the qury `size` returning the number of elements in the tree (the number of elements added)
- **Sheet 5, Task 1**: The `Rectangle` class needs a method `fullyContaines(Rectangle rectangle)` which checkes weather the given rectangle is fully contained whithin the rectangle the method is called upon. This method is already implemented (if you want the challange of doing it yourself, just reset the `Rectangle` class to the state of the code given by VIS)

## Authors
Niklas Krieger, Silas Santos, Georg Reißner, VIS

## License
We'd love to put it under a open-source license but we can't because we don't have the rights to the all the sources as e.g. the project structure etc. was published by VIS; so please ask us (and the people from VIS) before passing on the sources...
If you want the repository taken down for any reason, please contact me via the mail address listed on my GitHub profile.