
import sys


def main():
    # Read input
    A = [int(line) for line in sys.stdin]

    A = mergesort(A)

    # Print result
    for num in A:
        print(num)


def mergesort(A):
    n = len(A)
    if n <= 1: return A
    i = n//2
    A1 = mergesort(A[:i])
    A2 = mergesort(A[i:])
    return merge(A1, A2, A)

def merge(A1, A2, A):
    i = 0
    j = 0
    while i < len(A1) and j < len(A2):
        if A1[i] < A2[j]:
            A[i+j] = A1[i]
            i += 1
        else:
            A[i+j] = A2[j]
            j += 1
    while i < len(A1):
        A[i+j] = A1[i]
        i += 1
    while j < len(A2):
        A[i+j] = A2[j]
        j += 1
    return A


if __name__ == "__main__":
    main()
