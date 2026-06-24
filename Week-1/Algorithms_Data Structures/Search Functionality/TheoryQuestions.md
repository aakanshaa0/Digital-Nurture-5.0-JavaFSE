# 1. What is Big O Notation?

Big O notation is used to measure the efficiency of an algorithm. It describes how the execution time of an algorithm grows as the input size increases. It helps developers compare different algorithms and choose the most efficient solution.

# 2. Best, Average and Worst Case Scenarios for Search Operations

## Best Case:
The required product is found in the first comparison.

## Average Case:
The required product is found after checking some of the elements in the collection.

## Worst Case:
The required product is found at the last position or is not present in the collection.

# 3. Comparison of Linear Search and Binary Search

## Linear Search:
The best-case time complexity is O(1) when the product is found in the first comparison. The average-case and worst-case time complexities are O(n) because the algorithm may need to examine most or all elements in the array.

## Binary Search:
The best-case time complexity is O(1) when the product is found at the middle position. The average-case and worst-case time complexities are O(log n) because the search space is reduced by half in each step.

# 4. Which Algorithm is More Suitable for the Platform?

Binary Search is more suitable for large e-commerce platforms because it performs fewer comparisons and provides faster search results. However, it requires the data to be sorted. Linear Search is easier to implement but becomes slower for large datasets.