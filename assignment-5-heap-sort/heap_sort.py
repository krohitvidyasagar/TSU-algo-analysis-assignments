import uuid
import random
from datetime import datetime, timedelta


def generate_job_id():
    return uuid.uuid4()

def generate_random_number(min: int, max: int):
    return random.randint(min, max)

def generate_job_name():
    job_names = ['Dig', 'Crawl', 'Jump', 'Stroll', 'Meander', 'Run', 'Sprint', 'Stride', 'Lob', 'Skate', 'Cycle', 'Fly']
    job_name_index = generate_random_number(0, len(job_names)-1)
    return job_names[job_name_index]

def generate_submitter_name():
    submitter_names = ['ron', 'dan', 'vince', 'mack', 'dom', 'ace', 'will', 'jones', 'pat', 'klint', 'mick', 'rick', 'jack']
    submiiter_name_index = generate_random_number(0, len(submitter_names)-1)
    return submitter_names[submiiter_name_index]

def generate_submission_date():
    number_of_days = generate_random_number(100, 200)
    return datetime.utcnow() + timedelta(days=number_of_days)


class Job:
    
    def __init__(self):
        self.number = generate_job_id()
        self.name = generate_job_name()
        self.submitter_name = generate_submitter_name()
        self.submission_date = generate_submission_date()
        self.priority = generate_random_number(1, 25)


def print_jobs(jobs):
    for job in jobs:
        print(f'Job: {job.number}  {job.submission_date}    {job.priority}      {job.submitter_name}   {job.name}')

def heapify(arr, N, i):
    largest = i
    left = 2 * i + 1
    right = 2 * i + 2

    # If left node exists and left node is greater than root, change largest to left
    if left < N and arr[largest].priority < arr[left].priority:
        largest = left

    # If right node exists and right node is greater than root, change largest to right
    if right < N and arr[largest].priority < arr[right].priority:
        largest = right

    # Change root if needed
    if largest != i:
        # Swapping the largest index of the array with i element of the array
        arr[i], arr[largest] = arr[largest], arr[i]

        # Heapify the root
        heapify(arr, N, largest)

def heap_sort(arr):
    N = len(arr)

    # Build a max heap
    for i in range(N//2 - 1, -1, -1):
        heapify(arr, N, i)

    # One by one extract elements
    for i in range(N-1, 0, -1):
        # Swap
        arr[i], arr[0] = arr[0], arr[i]

        heapify(arr, i, 0)


def __init__():
    wait_queue: list = []
    ready_queue: list = []

    # Number of jobs to be added to Wait Queue
    number_of_jobs = generate_random_number(10, 15)

    # Enter jobs into Wait Queue
    for job in range(0, number_of_jobs):
        job = Job()
        wait_queue.append(job)

    # Display the jobs in Wait Queue
    print('Printing jobs in Wait Queue \n')
    print_jobs(wait_queue)

    # Heapify the Wait Queue
    heap_sort(wait_queue)

    # Display the jobs sorted by priority in Wait Queue
    print('\n\nPrinting jobs sorted by priority in Wait Queue \n')
    print_jobs(wait_queue)

    # Move 5 jobs from Wait Queue to Ready Queue
    for i in range(0, 5, 1):
        size_of_wait_queue = len(wait_queue)

        # Extract largest priority job from Wait Queue
        largest_priority_job = wait_queue.pop(size_of_wait_queue - 1)
        
        # Heapify Wait Queue
        heap_sort(wait_queue)

        # Append the extracted job to Ready Queue
        ready_queue.append(largest_priority_job)

        # Heapify Ready Queue
        heap_sort(ready_queue)

    print('\n\nPrinting the jobs in Wait Queue\n')
    print_jobs(wait_queue)

    print('\n\nPrinting the jobs in Ready Queue\n')
    print_jobs(ready_queue)


    # Delete 3 jobs from Ready Queue
    for i in range(0, 3, 1):
        # Popping highest priority job from Ready Queue
        ready_queue.pop(len(ready_queue) - 1)

        # Heapifying the popped Ready Queue
        heap_sort(ready_queue)

        print('\n\nPrinting the jobs in popped and sorted Ready Queue\n')
        print_jobs(ready_queue)

        # Adding jobs to Wait Queue
        job = Job()
        wait_queue.append(job)

    heap_sort(wait_queue)

    for i in range(0, 4, 1):
        high_priority_wait_queue_job = wait_queue.pop(len(wait_queue) - 1)

        ready_queue.append(high_priority_wait_queue_job)

    for i in range(0, 2, 1):
        ready_queue.pop(len(ready_queue) - 1)

    heap_sort(wait_queue)
    heap_sort(ready_queue)

    print('\n\nSorted Wait Queue\n')
    print_jobs(wait_queue)

    print('\n\nSorted Ready Queue\n')
    print_jobs(ready_queue)

    # Change priority of 3 jobs in Wait Queue
    for i in range(0, 3, 1):
        index_of_job_priority_to_be_changed = generate_random_number(0, len(wait_queue)- 1)

        wait_queue[index_of_job_priority_to_be_changed].priority = generate_random_number(1, 25)

    heap_sort(wait_queue)
    print('\n\nPrinting Wait Queue after changing priority of 3 jobs\n')
    print_jobs(wait_queue)

    wait_queue.clear()

    ready_queue.clear()


__init__()