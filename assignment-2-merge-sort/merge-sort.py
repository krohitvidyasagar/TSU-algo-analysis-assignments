import sys
import random
import math

from prettytable import PrettyTable
import matplotlib.pyplot as plt
import numpy as np

def initialize_counter():
    return [0]

def increment_counter(counter: list):
    counter[0] = counter[0] + 1

def generate_random_number(min: int, max: int):
    return random.randint(min, max)

def get_random_list(length: int):
    arr = []
    for i in range(length):
        arr.append(generate_random_number(0, 100))

    return arr

def merge(arr: list, begin: int, mid: int, end: int, counter: list):

    n1 = mid - begin + 1
    n2 = end - mid
    
    left_array = []
    for num in range(0, n1):
        left_array.append(arr[begin + num])
        
    right_array = []
    for num in range(0, n2):
        right_array.append(arr[mid + 1 + num])
        
    left_array.append(sys.maxsize)
    right_array.append(sys.maxsize)

    i = 0
    j = 0
    k = begin
    
    while i < n1 and j < n2:
        if left_array[i] <= right_array[j]:
            increment_counter(counter)
            arr[k] = left_array[i]
            i += 1
        else:
            increment_counter(counter)
            arr[k] = right_array[j]
            j += 1
        k += 1

    while i < n1:
        arr[k] = left_array[i]
        i += 1
        k += 1

    while j < n2:
        arr[k] = right_array[j]
        j += 1
        k += 1
    


def merge_sort(arr: list, begin: int, end: int, counter: list):
    if begin < end:
        mid = begin + (end - begin) // 2

        merge_sort(arr, begin, mid, counter)

        merge_sort(arr, mid+1, end, counter)
        
        merge(arr, begin, mid, end, counter)


def __init__():        
    # Minimum number of sets
    min_sets = 10
    # Maximum number of sets
    max_sets = 20

    number_of_sets = generate_random_number(min_sets, max_sets)

    table = PrettyTable(['Input', 'Actual Count', 'T(N)'])

    input_to_counter_dict = dict()

    for x in range(number_of_sets):
        # Initialize array size
        arr_size = generate_random_number(30, 50)

        # Initialize a random array
        arr = get_random_list(arr_size)

        # Print unsorted array
        print(f'Unsorted Array  : {arr}')

        counter = initialize_counter()

        # Sort the array
        merge_sort(arr, 0, arr_size - 1, counter)

        # Print sorted array
        print(f'Sorted Array   : {arr}')

        input_to_counter_dict[arr_size] = counter[0]

        table.add_row([arr_size, input_to_counter_dict[arr_size], round(arr_size + (arr_size * math.log(arr_size, 2)), 2)])
        
        print('\n')

    print('\n\n')

    print(table)

    input_array = []
    counter_array = []
    avg_time_complexity_array = []

    for key in input_to_counter_dict:
        input_array.append(key)
        counter_array.append(input_to_counter_dict[key])
        avg_time_complexity_array.append(round(key + (key * math.log(key, 2)), 2))

    input_array.sort()
    counter_array.sort()
    avg_time_complexity_array.sort()

   
    x1 = np.array(input_array)
    y1 = np.array(counter_array)
    y2 = np.array(avg_time_complexity_array)


    plt.xlabel('Number of Inputs')
    plt.ylabel('Time Complexity')
    plt.title('Merge Sort Time Complexity')

    plt.xlim(30, 50)

    plt.plot(x1, y1, label='Actual Count')

    plt.plot(x1, y2, label='T(N)')

    print('\n\n')
    print(plt.show())

__init__()
