import re
import sys
import random

def generate_random_number(min: int, max: int):
    """
    Accepts min and max integer value as range to return a random number within that range
    """
    return random.randint(min, max)

def get_random_list(length: int):
    """
    Accepts length integer and return a random list of numbers of the specified length
    """
    arr = []
    for i in range(0, length):
        arr.append(generate_random_number(-20, 20))

    return arr

def max_sub_array_using_brute_force(arr):
    """
    Accepts a random array and returns the maximum sum, beginning index and last index of the sub using brute-force method 
    """
    # Initialize the max as the first element of the array
    max = arr[0]
    left_index = right_index = 0

    for i in range(0, len(arr)):
        # Initialize current sum with every iteration of the outer-loop
        currentSum = 0
        for j in range(i, len(arr)):
            # Add array elements to current-sum to maintain
            currentSum += arr[j]

            # Replace the max, left_index and right_index if current-sum is greater than max
            if currentSum > max:
                max = currentSum
                left_index = i
                right_index = j

    return max, left_index, right_index

def max_crossing_sub_array_sum(arr, left_index, mid_index, right_index):
    """
    """
    current_sum = 0
    left_max_sum = -sys.maxsize

    for i in range(mid_index, left_index - 1, -1):
        current_sum = current_sum + arr[i]

        if current_sum > left_max_sum:
            left_max_sum = current_sum

    current_sum = 0
    right_max_sum = -sys.maxsize

    for j in range(mid_index, right_index + 1):
        current_sum = current_sum + arr[j]

        if current_sum > right_max_sum:
            right_max_sum = current_sum

    crossing_sum = left_max_sum + right_max_sum - arr[mid_index]

    if crossing_sum > left_max_sum and crossing_sum > right_max_sum:
        return crossing_sum
    elif left_max_sum > crossing_sum and left_max_sum > right_max_sum:
        return left_max_sum
    else:
        return right_max_sum


def max_sub_array_using_recursion(arr, left_index, right_index):
    """
    """
    if left_index >= right_index:
        return arr[left_index]

    mid_index = (left_index + right_index) // 2

    left_sub_array_sum = max_sub_array_using_recursion(arr, left_index, mid_index-1)
    
    right_sub_array_sum = max_sub_array_using_recursion(arr, mid_index+1, right_index)
    
    crossing_sub_array_sum = max_crossing_sub_array_sum(arr, left_index, mid_index, right_index)

    if left_sub_array_sum > right_sub_array_sum  and left_sub_array_sum > crossing_sub_array_sum:
        return left_sub_array_sum
    elif right_sub_array_sum > left_sub_array_sum and right_sub_array_sum > crossing_sub_array_sum:
        return right_sub_array_sum
    else:
        return crossing_sub_array_sum
    

def __init__():
    min_number_of_sets = 6
    max_number_of_sets = 8

    total_number_of_sets = generate_random_number(min_number_of_sets, max_number_of_sets)

    for num in range(0, total_number_of_sets):
        array_length = generate_random_number(5, 10)

        array = get_random_list(array_length)

        print(f'Original array: {array}')

        max_sum_brute_force, left_index_brute_force, right_index_brute_force = max_sub_array_using_brute_force(array)

        print('Using brute-force')

        print(f'Max Sum: {max_sum_brute_force}, left index: {left_index_brute_force + 1}, right index: {right_index_brute_force + 1}\n')

        print('Using recursion')
        max_sum_recursion = max_sub_array_using_recursion(array, 0, array_length - 1)
        print(f'Max Sum: {max_sum_recursion}')

        print('\n')

__init__()
