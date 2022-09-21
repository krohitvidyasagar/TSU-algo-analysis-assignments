import math
import sys
import random

recursion_counter = [0]

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
        arr.append(generate_random_number(-999, 999))

    return arr

def max_sub_array_using_brute_force(arr):
    """
    Accepts a random array and returns the maximum sum, beginning index and last index of the sub using brute-force method 
    """
    counter = 0

    # Initialize the max as the first element of the array
    max_sum = -sys.maxsize

    left_index = right_index = 0

    for i in range(0, len(arr)):
        counter += 1

        # Initialize current sum with every iteration of the outer-loop
        current_sum = 0

        for j in range(i, len(arr)):
            counter += 1

            # Add array elements to current-sum to maintain
            current_sum += arr[j]

            # Replace the max, left_index and right_index if current-sum is greater than max
            if current_sum > max_sum:
                counter += 1

                max_sum = current_sum

                left_index = i
                right_index = j

    return max_sum, left_index, right_index, counter

def max_crossing_sub_array_sum(arr, left_index, mid_index, right_index):
    """
    """
    current_sum = 0
    left_max_sum = -sys.maxsize
    left_max_begin_index = mid_index

    for i in range(mid_index, left_index - 1, -1):
        current_sum = current_sum + arr[i]

        if current_sum > left_max_sum:
            recursion_counter[0] += 1
            left_max_sum = current_sum
            left_max_begin_index = i

    current_sum = 0
    right_max_sum = -sys.maxsize
    right_max_end_index = mid_index

    for j in range(mid_index, right_index + 1):
        current_sum = current_sum + arr[j]

        if current_sum > right_max_sum:
            recursion_counter[0] += 1
            right_max_sum = current_sum
            right_max_end_index = j

    crossing_sum = left_max_sum + right_max_sum - arr[mid_index]

    if crossing_sum > left_max_sum and crossing_sum > right_max_sum:
        return crossing_sum, left_max_begin_index, right_max_end_index
    elif left_max_sum > crossing_sum and left_max_sum > right_max_sum:
        return left_max_sum, left_max_begin_index, mid_index
    else:
        return right_max_sum, mid_index, right_max_end_index

def max_sub_array_using_recursion(arr, left_index, right_index):
    """
    Accepts and array, the left index(begin) of the array and the right index (end) of the array and returns
    the maximum sum of a subarray and the beginning index and the final index of the subarray
    """
    if left_index >= right_index:
        return arr[left_index], left_index, right_index

    mid_index = (left_index + right_index) // 2

    left_sub_array_sum, left_begin_index, left_end_index = max_sub_array_using_recursion(arr, left_index, mid_index)
    
    right_sub_array_sum, right_begin_index, right_end_index = max_sub_array_using_recursion(arr, mid_index+1, right_index)
    
    crossing_sub_array_sum, crossing_begin_index, crossing_end_index = max_crossing_sub_array_sum(arr, left_index, mid_index, right_index)

    if left_sub_array_sum > right_sub_array_sum  and left_sub_array_sum > crossing_sub_array_sum:
        return left_sub_array_sum, left_begin_index, left_end_index
    elif right_sub_array_sum > left_sub_array_sum and right_sub_array_sum > crossing_sub_array_sum:
        return right_sub_array_sum, right_begin_index, right_end_index
    else:
        return crossing_sub_array_sum, crossing_begin_index, crossing_end_index
    

def __init__():
    min_number_of_sets = 6
    max_number_of_sets = 10

    total_number_of_sets = generate_random_number(min_number_of_sets, max_number_of_sets)

    brute_force_input_to_counter_dict = dict()

    for num in range(0, total_number_of_sets):
        array_length = generate_random_number(50, 70)

        array = get_random_list(array_length)

        print('===============================================================================================================')
        print(f'\nOriginal array: {array}')

        max_sum_brute_force, left_index, right_index, brute_force_counter = max_sub_array_using_brute_force(array)
        brute_force_input_to_counter_dict[array_length] = brute_force_counter

        print('\nMax subarray sum using brute-force')

        print(f'Max Sum: {max_sum_brute_force}, left index: {left_index + 1}, right index: {right_index + 1}')
        print(f'Sub-array: {array[left_index: right_index + 1]}')
        print(f'Number of inputs: {array_length}, Counter: {brute_force_counter}, Worst case: {array_length * array_length}')

        recursion_counter[0] = 0

        print('\nMax subarray sum using recursion')
        max_sum_recursion, left_index, right_index = max_sub_array_using_recursion(array, 0, array_length - 1)
        print(f'Max Sum: {max_sum_recursion}, left index: {left_index + 1}, right index: {right_index + 1}')
        print(f'Sub-array: {array[left_index: right_index + 1]}')
        print(f'Number of inputs: {array_length}, Counter: {recursion_counter[0]}, Worst case: {round((array_length * math.log(array_length, 2)), 2)}')


__init__()
